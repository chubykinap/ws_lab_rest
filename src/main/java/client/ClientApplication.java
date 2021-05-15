package client;

import com.sun.jersey.api.client.Client;

import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        Client client = Client.create();
        ClientFlightService flightService = new ClientFlightService(client);
        System.out.println("Введите одну из команд:\r\ninsert, update, delete, get");
        String method = new Scanner(System.in).nextLine(), request;
        try {
            switch (method) {
                case "insert":
                    System.out.println("Команда добавления\r\n" +
                            "Для использования напишите значения через запятую в одинарных кавычках в порядке\r\n" +
                            "flight_number, departure_date, departure_city, arrival_city, aircraft_type");
                    request = new Scanner(System.in).nextLine();
                    flightService.addFlight(request);
                    return;
                case "update":
                    System.out.println("Команда изменения\r\n" +
                            "Для использования напишите через пробел номер записи, поля для обновления (через запятую) " +
                            "и значения (через запятую) в порядке:\r\n" +
                            "flight_number,departure_date,departure_city,arrival_city,aircraft_type");
                    request = new Scanner(System.in).nextLine();
                    flightService.changeFlight(request);
                    return;
                case "delete":
                    System.out.println("Команда удаления\r\n" +
                            "Введите номер записи для удаления");
                    request = new Scanner(System.in).nextLine();
                    int id = Integer.parseInt(request);
                    flightService.deleteFlight(id);
                    return;
                case "get":
                    System.out.println("Команда запроса на получение списка\r\n" +
                            "Запишите через пробел поле для поиска и его значение");
                    request = new Scanner(System.in).nextLine();
                    flightService.getFlights(request);
                    return;
                default:
                    System.out.println("Команда не найдена");
            }
        } catch (Exception e) {
            System.out.println("Сообщение об ошибке от сервиса:\r\n" + e.getMessage());
        }
    }
}
