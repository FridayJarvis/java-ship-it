package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.Parcels.Parcel;
import ru.yandex.practicum.delivery.Parcels.StandardParcel;

import java.util.ArrayList;
import java.util.List;

public class ParcelBox<T extends Parcel> {
    private List<T> parcels;
    private int maxWeight;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.parcels = new ArrayList<>();
        this.maxWeight = maxWeight;
        this.currentWeight = 0;
    }

    public void addParcel(T parcel) {
        int parcelWeight = parcel.getWeight();

        if (parcelWeight + currentWeight > maxWeight) {
            System.out.println("Посылка весом " + parcelWeight + "кг не вмещается в данную коробку. Текущий вес коробки " + currentWeight +
                    "кг. Максимальный вес этой коробки: " + maxWeight + "кг\n");
            return;
        }

        parcels.add(parcel);
        currentWeight += parcelWeight;
        System.out.println("Посылка вместилась в коробку\n");
    }

    public List<T> getAllParcels() {
        return parcels;
    }
}
