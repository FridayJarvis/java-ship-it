package ru.yandex.practicum.delivery.Parcels;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.delivery.ParcelBox;

public class ParcelBoxTest {
    private static final int MAX_WEIGHT = 10;

    private ParcelBox<StandardParcel> standardBox;

    @BeforeEach
    void setUp() {
        standardBox = new ParcelBox<>(MAX_WEIGHT);
    }

    @Test
    void shouldAddParcelWhenWeightDoesNotExceedMaxWeight() {
        StandardParcel parcel = new StandardParcel("", "", 2, 1);
        standardBox.addParcel(parcel);

        assertEquals(1, standardBox.getAllParcels().size());
        assertTrue(standardBox.getAllParcels().contains(parcel));
    }

    @Test
    void shouldAddParcelWhenWeightEqualsMaxWeight() {
        StandardParcel parcel = new StandardParcel("", "", MAX_WEIGHT, 1);
        standardBox.addParcel(parcel);

        assertEquals(1, standardBox.getAllParcels().size());
        assertTrue(standardBox.getAllParcels().contains(parcel));
    }

    @Test
    void shouldNotAddParcel() {
        StandardParcel parcel = new StandardParcel("", "", MAX_WEIGHT + 1, 1);
        standardBox.addParcel(parcel);

        assertEquals(0, standardBox.getAllParcels().size());
        assertFalse(standardBox.getAllParcels().contains(parcel));
    }
}
