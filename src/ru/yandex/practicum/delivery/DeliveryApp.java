package ru.yandex.practicum.delivery;

import ru.yandex.practicum.delivery.Parcels.FragileParcel;
import ru.yandex.practicum.delivery.Parcels.Parcel;
import ru.yandex.practicum.delivery.Parcels.PerishableParcel;
import ru.yandex.practicum.delivery.Parcels.StandardParcel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static List<Parcel> allParcels = new ArrayList<>();
    private static List<Trackable> trackableParcels = new ArrayList<>();

    private static ParcelBox<StandardParcel> standardParcelBox = new ParcelBox<>(25);
    private static ParcelBox<PerishableParcel> perishableParcelBox = new ParcelBox<>(12);
    private static ParcelBox<FragileParcel> fragileParcelBox = new ParcelBox<>(10);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            showGeneralMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addParcel();
                    break;
                case 2:
                    sendParcels();
                    break;
                case 3:
                    calculateCosts();
                    break;
                case 4:
                    handleReportStatus();
                    break;
                case 5:
                    handleBoxContent();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Неверный выбор.");
            }
        }

    }

    private static void showGeneralMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 — Добавить посылку");
        System.out.println("2 — Отправить все посылки");
        System.out.println("3 — Посчитать стоимость доставки");
        System.out.println("4 — Ввести расположение хрупкой посылки");
        System.out.println("5 — Показать содержимое коробки");
        System.out.println("0 — Завершить");
    }

    private static void showMenuForAdd() {
        System.out.println("1 - Стандартная посылка");
        System.out.println("2 - Скоропортящаяся посылка");
        System.out.println("3 - Хрупкая посылка");
        System.out.println("0 - Назад, в главное меню");
        System.out.println("Введите тип посылки, которую хотите добавить:");

    }

    private static void addParcel() {
        // Подсказка: спросите тип посылки и необходимые поля, создайте объект и добавьте в allParcels
        int choice;
        while (true) {
            showMenuForAdd();
            choice = Integer.parseInt(scanner.nextLine());

            if (choice == 0) {
                System.out.println("Вы вышли назад!\n");
                return;
            }
            if (choice > 0) break;

            System.out.println("Введено отрицательное число");
        }

        Parcel parcel = null;
        String deliveryAddress = getCorrectInputString("адрес доставки");
        String description = getCorrectInputString("описание");
        int weight = getCorrectInputInt("вес посылки");
        int sendDay = getCorrectInputInt("дата отправки посылки");

        switch (choice) {
            case 1:
                parcel = new StandardParcel(description, deliveryAddress, weight, sendDay);
                standardParcelBox.addParcel((StandardParcel) parcel);
                break;
            case 2:
                int timeToLive = getCorrectInputInt("срок годности");
                parcel = new PerishableParcel(description, deliveryAddress, weight, sendDay, timeToLive);
                perishableParcelBox.addParcel((PerishableParcel) parcel);
                break;
            case 3:
                parcel = new FragileParcel(description, deliveryAddress, weight, sendDay);
                trackableParcels.add((FragileParcel)parcel);
                fragileParcelBox.addParcel((FragileParcel) parcel);
                break;
        }

        System.out.println("Посылка \"" + parcel.getDescription() + "\" успешно добавлена\n");
        allParcels.add(parcel);
    }

    private static void sendParcels() {
        // Пройти по allParcels, вызвать packageItem() и deliver()
        for (Parcel parcel : allParcels) {
            parcel.packageParcel();
            parcel.deliver();
        }
        allParcels.clear();
        trackableParcels.clear();
    }

    private static void calculateCosts() {
        // Посчитать общую стоимость всех доставок и вывести на экран
        int totalCost = 0;
        for (Parcel parcel : allParcels) {
            totalCost += parcel.calculateDeliveryCost();
        }
        System.out.println("Общая стоимость отправки: " + totalCost + "\n");
    }

    private static void handleReportStatus() {
        showTrackableParcels();

        int numberOfTrackable;
        while (true) {
            System.out.println("Введите номер отслеживаемой посылки:");
            numberOfTrackable = Integer.parseInt(scanner.nextLine());
            if (0 < numberOfTrackable && numberOfTrackable <= trackableParcels.size())
                break;
            System.out.println("Номер отслеживаемой посылки от 1 до " + trackableParcels.size() + ". Введите еще раз\n");
        }

        String newLocation = getCorrectInputString("новое местоположение посылки");

        final int INDEX = numberOfTrackable - 1;
        Trackable trackable = trackableParcels.get(INDEX);
        trackable.reportStatus(newLocation);
    }

    private static void showTrackableParcels() {
        for (int i = 0; i < trackableParcels.size(); ++i) {
            System.out.println("Посылка №" + (i + 1) + " " + trackableParcels.get(i).getDescription());
        }
    }

    private static <T extends Parcel> void handleBoxContent() {
        showBoxContentsMenu();
        int cmd = scanner.nextInt();

        switch (cmd) {
            case 1:
                showThingInBox(standardParcelBox);
                break;
            case 2:
                showThingInBox(perishableParcelBox);
                break;
            case 3:
                showThingInBox(fragileParcelBox);
                break;
            case 0:
                System.out.println("Вы вышли назад\n");
                return;
            default:
                System.out.println("Неверная команда");
        }
    }

    private static void showBoxContentsMenu() {
        System.out.println("1 — Показать содержимое стандартной коробки");
        System.out.println("2 — Показать содержимое скоропортящейся коробки");
        System.out.println("3 — Показать содержимое хрупкой коробки");
        System.out.println("0 — Назад");
    }

    private static <T extends Parcel> void showThingInBox(ParcelBox<T> box) {
        List<T> parcelsInBox = box.getAllParcels();
        for (T parcel : parcelsInBox) {
            System.out.println(parcel.getDescription());
        }
    }

    private static int getCorrectInputInt(String whatIsData) {
        int correctInput;
        while (true) {
            System.out.println("Введите поле \"" + whatIsData + "\":");
            correctInput = Integer.parseInt(scanner.nextLine());

            if (correctInput > 0) {
                System.out.println("Поле \"" + whatIsData + "\" успешно добавлено\n");
                return correctInput;
            }

            System.out.println("Поле \"" + whatIsData + "\" должно быть больше 0!");
        }
    }

    private static String getCorrectInputString(String whatIsData) {
        String correctInput;
        while (true) {
            System.out.println("Введите поле \"" + whatIsData + "\":");
            correctInput = scanner.nextLine();

            if (!correctInput.isEmpty()) {
                System.out.println("Поле \"" + whatIsData + "\" успешно добавлено\n");
                return correctInput;
            }

            System.out.println("Поле \"" + whatIsData + "\" не должно быть пустым");
        }
    }
}

