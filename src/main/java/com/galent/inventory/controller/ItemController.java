package com.galent.inventory.controller;

import com.galent.inventory.model.Item;
import com.galent.inventory.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/** REST surface for the inventory service. See docs/architecture/index.md. */
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> list() {
        return itemService.listAll();
    }

    @GetMapping("/{sku}")
    public Item bySku(@PathVariable String sku) {
        return itemService.getBySku(sku);
    }

    @GetMapping("/low-stock")
    public List<Item> lowStock() {
        return itemService.lowStock();
    }

    @GetMapping("/stock-value")
    public BigDecimal stockValue() {
        return itemService.totalStockValue();
    }

    @PostMapping("/{sku}/receive")
    public ResponseEntity<Item> receive(@PathVariable String sku, @RequestParam int quantity) {
        return ResponseEntity.ok(itemService.receiveStock(sku, quantity));
    }
}
