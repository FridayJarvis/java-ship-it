package ru.yandex.practicum.delivery.Parcels;

import ru.yandex.practicum.delivery.Trackable;

public class FragileParcel extends Parcel implements Trackable {
    private static final int FRAGILE_PARCEL_COST = 4;

    public FragileParcel(String description, String deliveryAddress, int weight, int sendDay) {
        super(description, deliveryAddress, weight, sendDay);
    }

    @Override
    public void packageParcel() {
        System.out.println("Посылка \"" + description + " обмотана в защитную плёнку");
        super.packageParcel();
    }

    @Override
    protected int pricePerKg() {
        return FRAGILE_PARCEL_COST;
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка \"" + description + "\" изменила местоположение на " + newLocation);
    }
}
