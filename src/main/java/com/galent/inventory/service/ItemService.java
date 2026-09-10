package com.galent.inventory.service;

import com.galent.inventory.model.Item;
import com.galent.inventory.repository.ItemRepository;
import com.galent.inventory.util.PriceCalculator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/** Inventory operations. Described in docs/architecture/Architecture.md. */
@Service
public class ItemService {

    private static final int LOW_STOCK_THRESHOLD = 10;

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> listAll() {
        return itemRepository.findAll();
    }

    public Item getBySku(String sku) {
        return itemRepository.findBySku(sku)
                .orElseThrow(() -> new ItemNotFoundException(sku));
    }

    /** Items that have fallen below the reorder threshold. */
    public List<Item> lowStock() {
        return itemRepository.findByQuantityOnHandLessThan(LOW_STOCK_THRESHOLD);
    }

    /** Total value of everything on hand, used by the daily stock report. */
    public BigDecimal totalStockValue() {
        return itemRepository.findAll().stream()
                .map(i -> PriceCalculator.stockValue(i.getUnitPrice(), i.getQuantityOnHand()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Transactional
    public Item receiveStock(String sku, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be positive");
        }
        Item item = getBySku(sku);
        item.setQuantityOnHand(item.getQuantityOnHand() + quantity);
        return itemRepository.save(item);
    }
}
