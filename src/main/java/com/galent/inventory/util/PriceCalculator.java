package com.galent.inventory.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/** Pricing rules. Kept separate so ItemService stays about orchestration. */
public final class PriceCalculator {

    private static final BigDecimal BULK_DISCOUNT = new BigDecimal("0.90");
    private static final int BULK_THRESHOLD = 100;

    private PriceCalculator() {
    }

    /** Line total, with the bulk discount applied above the threshold. */
    public static BigDecimal lineTotal(BigDecimal unitPrice, int quantity) {
        BigDecimal gross = unitPrice.multiply(BigDecimal.valueOf(quantity));
        if (quantity >= BULK_THRESHOLD) {
            gross = gross.multiply(BULK_DISCOUNT);
        }
        return gross.setScale(2, RoundingMode.HALF_UP);
    }

    /** Value of stock on hand for one item. */
    public static BigDecimal stockValue(BigDecimal unitPrice, int quantityOnHand) {
        return unitPrice.multiply(BigDecimal.valueOf(quantityOnHand))
                .setScale(2, RoundingMode.HALF_UP);
    }
}
