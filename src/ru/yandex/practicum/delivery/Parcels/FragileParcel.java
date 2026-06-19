package ru.yandex.practicum.delivery.Parcels;

import ru.yandex.practicum.delivery.Trackable;

public class FragileParcel extends Parcel implements Trackable {
    public FragileParcel(String description, String deliveryAddress, int weight, int sendDay) {
        super(description, deliveryAddress, weight, sendDay);
    }

    @Override
    public void packageParcel() {
        System.out.println("Посылка \"" + description + " обмотана в защитную плёнку");
        System.out.println("Посылка \"" + description + " упакована");
    }

    @Override
    protected int pricePerKg() {
        return getFragileParcelCost();
    }

    @Override
    public void reportStatus(String newLocation) {
        System.out.println("Хрупкая посылка \"" + description + "\" изменила местоположение на " + newLocation);
    }
}
