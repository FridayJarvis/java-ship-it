package ru.yandex.practicum.delivery.Parcels;

public class StandardParcel extends Parcel {
    public StandardParcel(String description, String deliveryAddress, int weight, int sendDay) {
        super(description, deliveryAddress, weight, sendDay);
    }

    @Override
    protected int pricePerKg() {
        return getStandardParcelCost();
    }
}
