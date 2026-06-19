package ru.yandex.practicum.delivery.Parcels;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FragileParcelTest {

    private static final int PRICE_PER_KG = 4;

    @Test
    void shouldCalculateDeliveryCostCorrectly() {
        int weight = 1;
        FragileParcel parcel = new FragileParcel("", "", weight, 1);
        int resultCost = parcel.calculateDeliveryCost();
        assertEquals(weight * PRICE_PER_KG, resultCost);

        weight = 10;
        parcel.setWeight(weight);
        resultCost = parcel.calculateDeliveryCost();
        assertEquals(weight * PRICE_PER_KG, resultCost);
    }
}
