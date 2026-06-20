package ru.yandex.practicum.delivery.Parcels;

public class StandardParcel extends Parcel {

    private static final int STANDARD_PARCEL_COST = 2;

    public StandardParcel(String description, String deliveryAddress, int weight, int sendDay) {
        super(description, deliveryAddress, weight, sendDay);
    }

    @Override
    protected int pricePerKg() {
        return STANDARD_PARCEL_COST;
    }
}
