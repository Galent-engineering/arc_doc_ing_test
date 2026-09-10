package com.galent.inventory.service;

/** Thrown when a SKU does not resolve to a stocked item. */
public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String sku) {
        super("No item with SKU " + sku);
    }
}
