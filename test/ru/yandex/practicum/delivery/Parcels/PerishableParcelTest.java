package ru.yandex.practicum.delivery.Parcels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PerishableParcelTest {
    private static final int PRICE_PER_KG = 3;

    @Test
    void shouldCalculateDeliveryCostCorrectly() {
        int weight = 1;
        PerishableParcel parcel = new PerishableParcel("", "", weight, 1, 1);
        int resultCost = parcel.calculateDeliveryCost();
        assertEquals(weight * PRICE_PER_KG, resultCost);

        weight = 10;
        parcel.setWeight(weight);
        resultCost = parcel.calculateDeliveryCost();
        assertEquals(weight * PRICE_PER_KG, resultCost);
    }

    @Test
    void perishableParcelShouldNotExpired() {
        int sendDay = 10;
        int timeToLive = 2;
        PerishableParcel perishableParcel = new PerishableParcel("", "", 1, sendDay, timeToLive);
        assertFalse(perishableParcel.isExpired(timeToLive + sendDay - 10));
        assertFalse(perishableParcel.isExpired(timeToLive + sendDay));
        assertTrue(perishableParcel.isExpired(timeToLive + sendDay + 1));
    }
}
