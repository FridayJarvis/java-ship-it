package ru.yandex.practicum.delivery.Parcels;

public class PerishableParcel extends Parcel {
    private static final int PERISHABLE_PARCEL_COST = 3;

    private int timeToLive;

    public PerishableParcel(String description, String deliveryAddress, int weight, int sendDay, int timeToLive) {
        super(description, deliveryAddress, weight, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    protected int pricePerKg() {
        return PERISHABLE_PARCEL_COST;
    }

    public boolean isExpired(int currentDay) {
        return sendDay + timeToLive < currentDay;
    }
}
