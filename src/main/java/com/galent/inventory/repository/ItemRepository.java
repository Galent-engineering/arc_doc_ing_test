package com.galent.inventory.repository;

import com.galent.inventory.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Optional<Item> findBySku(String sku);

    List<Item> findByQuantityOnHandLessThan(int threshold);

    @Query("select i from Item i where i.category.code = ?1")
    List<Item> findByCategoryCode(String categoryCode);
}
