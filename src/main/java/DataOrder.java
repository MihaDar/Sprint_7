

public class DataOrder extends Url{

    String firstName = "Misha";
    String lastName = "Mishin";
    String address = "Moskva, 77";
    String metroStation = "4";
    String phone = "+7 800 355 35 35";
    int rentTime = 4;
    String deliveryDate = "2023-08-06";
    String comment = "Go Go Go";
    String[] color = new String[]{"BLACK"};

    CreateOrder request = new CreateOrder(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color);

}
