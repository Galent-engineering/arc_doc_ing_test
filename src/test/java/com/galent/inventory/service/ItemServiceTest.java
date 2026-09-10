package com.galent.inventory.service;

import com.galent.inventory.util.PriceCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    @Test
    void bulkDiscountAppliesAtTheThreshold() {
        BigDecimal atThreshold = PriceCalculator.lineTotal(new BigDecimal("2.00"), 100);
        assertEquals(new BigDecimal("180.00"), atThreshold);
    }

    @Test
    void belowThresholdIsUndiscounted() {
        BigDecimal below = PriceCalculator.lineTotal(new BigDecimal("2.00"), 99);
        assertEquals(new BigDecimal("198.00"), below);
    }

    @Test
    void stockValueRoundsToCents() {
        assertEquals(new BigDecimal("33.33"), PriceCalculator.stockValue(new BigDecimal("11.11"), 3));
    }
}
