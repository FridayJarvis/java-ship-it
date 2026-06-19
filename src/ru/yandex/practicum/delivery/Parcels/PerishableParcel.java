package ru.yandex.practicum.delivery.Parcels;

public class PerishableParcel extends Parcel {
    private int timeToLive;

    public PerishableParcel(String description, String deliveryAddress, int weight, int sendDay, int timeToLive) {
        super(description, deliveryAddress, weight, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    protected int pricePerKg() {
        return getPerishableParcelCost();
    }

    public boolean isExpired(int currentDay) {
        return sendDay + timeToLive < currentDay;
    }
}
