package ru.yandex.practicum.delivery.Parcels;

public abstract class Parcel {


    protected String description;
    protected String deliveryAddress;

    protected int weight;
    protected int sendDay;

    protected Parcel(String description, String deliveryAddress, int weight, int sendDay) {
        this.description = description;
        this.deliveryAddress = deliveryAddress;
        this.weight = weight;
        this.sendDay = sendDay;
    }

    public void packageParcel() {
        System.out.println("Посылка \"" + description + "\" упакована");
    }

    public void deliver() {
        System.out.println("Посылка \"" + description + "\" доставлена по адресу " + deliveryAddress + "\n");
    }

    public int calculateDeliveryCost() {
        return weight * pricePerKg();
    }

    protected abstract int pricePerKg();

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSendDay() {
        return sendDay;
    }

    public void setSendDay(int sendDay) {
        this.sendDay = sendDay;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
