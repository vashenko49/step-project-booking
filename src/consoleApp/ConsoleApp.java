package consoleApp;

import booking.Booking;
import flight.Flight;
import service.valid.Validation;
import booking.BookingController;
import booking.BookingService;
import booking.CollectionBookingDAO;
import flight.CollectionFlightDAO;
import flight.FlightController;
import flight.FlightService;
import users.CollectionUsersDAO;
import users.User;
import users.UsersController;
import users.UsersService;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleApp {
    private final Scanner scanner;
    private BookingController bookingController;
    private FlightController flightController;
    private UsersController usersController;

    private User currentUser;
    private boolean isExit = true;

    private final List<OperationApp> mainCommands = new ArrayList<>(Arrays.asList(
            new OperationApp("Онайн-табло", this::printOnlineTable),
            new OperationApp("Посмотреть информацию о рейсе", this::informationAboutFlight),
            new OperationApp("Выход", this::exit)
    ));


    private final List<OperationApp> logInRegister = new ArrayList<>(Arrays.asList(
            new OperationApp("Войти", this::logIn),
            new OperationApp("Регестрация", this::registration),
            new OperationApp("Продолжить", this::exit)
    ));


    private final List<OperationApp> inSession = new ArrayList<>(Arrays.asList(
            new OperationApp("Поиск и бронировка рейса", this::findAndBookingFlight),
            new OperationApp("Отменить бронирование", this::cancelBooking),
            new OperationApp("Мои бронирования", this::myBooking)
    ));


    private final OperationApp closeSession = new OperationApp("Завершшить сессию", this::logOut);
    private final OperationApp logInOrRegistration = new OperationApp("Войти или зарегестрироваться", this::registrationAndLogInProcedure);

    private final List<OperationApp> admin = new ArrayList<>(Arrays.asList(
            new OperationApp("Создать рейс", this::createFlight),
            new OperationApp("Удалить рейс", this::deleteFlight),
            new OperationApp("Список всех рейсов", this::getAllFlights),
            new OperationApp("Сохранить изменения", this::saveChange)
    ));


    List<OperationApp> custom = new ArrayList<>(mainCommands);

    private void saveChange() {
        if (usersController.saveDataToFile() && flightController.saveDataToFile() && bookingController.saveDataToFile()) {
            System.out.println("Успешно сохранено");
        } else {
            System.out.println("Ошибка при сохранении");
        }

    }

    private void registrationAndLogInProcedure() {
        while (isExit) {
            printCommand(logInRegister);
            OperationApp operationApp = logInRegister.get(Validation.scanInteger(1, logInRegister.size()) - 1);
            System.out.println(operationApp.operationName);
            operationApp.operation.operation();
        }
        if (Objects.isNull(currentUser) && !custom.contains(logInOrRegistration)) {
            custom.add(logInOrRegistration);
        }
        isExit = true;
    }

    public void run(boolean isAdmin) {
        if (isAdmin) {
            custom.addAll(admin);
        } else {
            custom.removeAll(admin);
        }
        registrationAndLogInProcedure();
        isExit = true;

        while (isExit) {
            printCommand(custom);
            OperationApp operationApp = custom.get(Validation.scanInteger(1, custom.size()) - 1);
            System.out.println(operationApp.operationName);
            operationApp.operation.operation();
        }
    }

    public void run() {
        run(false);
    }

    private void exit() {
        isExit = false;
    }

    private void getAllFlights() {
        for (Flight flight : flightController.getAllFlights()) {
            System.out.println(flight.toString());
        }
    }

    private void deleteFlight() {
        System.out.print("id рейса -> ");
        int flightId = Validation.scanInteger();
        if (flightController.deleteFlight(flightController.getFlightBuFlightId(flightId))) {
            System.out.println("Удаление успешно");
        } else {
            System.out.println("Удаление провалилось");
        }
    }

    private void createFlight() {
        System.out.print("Enter departure city -> ");
        String from = scanner.next();
        System.out.print("Enter arrival city -> ");
        String to = scanner.next();
        System.out.print("Enter departure time (dd/mm/yyyy-hh:mm) -> ");
        long departureTime = Validation.scanDate();
        System.out.print("Enter arrival time (dd/mm/yyyy-hh:mm) -> ");
        long arrivalTime = Validation.scanDate();
        System.out.print("Enter number of free place -> ");
        int numberOfFreePlaces = Validation.scanInteger();
        if (flightController.createFlight(new Flight(from, to, ThreadLocalRandom.current().nextInt(0, 100), departureTime, arrivalTime, numberOfFreePlaces))) {
            System.out.println("Успешно создано");
        } else {
            System.out.println("Ошибка при создании");
        }
    }


    private void cancelBooking() {
        System.out.print("Enter id booking -> ");
        int idBooking = Validation.scanInteger();
        Booking booking = bookingController.getBookingByBookingId(idBooking);
        if (Objects.nonNull(booking)) {
            if (bookingController.deleteBooking(booking)) {
                flightController.cancelBookingFlight(booking.getIdFlight());
                System.out.println("Booking cancel");
            } else {
                System.out.println("Fail booking cancel");
            }
        } else {
            System.out.println("Not Found");
        }

    }

    private void findAndBookingFlight() {
        System.out.print("From -> ");
        String from = scanner.next();
        System.out.print("To -> ");
        String to = scanner.next();
        System.out.print("Enter departure time (dd/mm/yyyy-hh:mm) -> ");
        long departureTime = Validation.scanDate();
        System.out.print("Number of passengers -> ");
        int numberOfPassengers = Validation.scanInteger();
        List<Flight> flights = flightController.findFlights(from, to, departureTime, numberOfPassengers);

        if (flights.size() > 0) {
            for (Flight flight : flights) {
                System.out.println(flight.prettyFormat());
            }
            System.out.println("1 - Забронировать рейс");
            System.out.println("2 - Назад");
            System.out.print("Command -> ");
            int isBooking = Validation.scanInteger(1, 2);

            if (isBooking == 1) {
                System.out.print("Enter id flight -> ");
                int flightId = Validation.scanInteger();
                if (Objects.nonNull(flightController.getFlightBuFlightId(flightId))) {
                    for (int i = 0; i < numberOfPassengers; i++) {
                        System.out.printf("Name %d passenger -> ", i + 1);
                        String namePassenger = scanner.next();
                        System.out.printf("Last name %d passenger -> ", i + 1);
                        String lastNamePassenger = scanner.next();
                        if (bookingFlight(currentUser.getLogin(), flightId, namePassenger, lastNamePassenger)) {
                            System.out.println("Booking success");
                        } else {
                            System.out.println("Booking fail");
                        }
                    }

                } else {
                    System.out.println("Not found flight");
                }
            }
        } else {
            System.out.println("No results were found for your request.");
        }
    }

    private boolean bookingFlight(String loginUser, int flightId, String namePassenger, String lastNamePassenger) {
        boolean result = bookingController.createBookingByLoginUserAndFlightId(loginUser, flightId, namePassenger, lastNamePassenger);
        if (result) {
            flightController.bookingFlight(flightId);
            return true;
        } else {
            return false;
        }
    }

    private void myBooking() {
        List<Booking> bookings = bookingController.getAllBookingsByLoginUser(currentUser.getLogin());
        StringBuilder stringBuilder = new StringBuilder();
        for (Booking booking : bookings) {
            stringBuilder.append("************************************************************\n");
            stringBuilder.append(booking.prettyFormat());
            stringBuilder.append(flightController.getFlightBuFlightId(booking.getIdFlight()).prettyFormat());
            stringBuilder.append("************************************************************\n");
        }
        System.out.println(stringBuilder.toString());
    }

    private void informationAboutFlight() {
        System.out.print("Enter id flight -> ");
        Flight flight = flightController.getFlightBuFlightId(Validation.scanInteger());
        if (!Objects.isNull(flight)) {
            System.out.println(flight.prettyFormat());
        } else {
            System.out.println("Not found");
        }
    }


    private void logOut() {
        currentUser = null;
        custom.removeAll(inSession);
        custom.remove(closeSession);
        if (!custom.contains(logInOrRegistration)) {
            custom.add(logInOrRegistration);
        }
    }

    public ConsoleApp() {
        this.scanner = new Scanner(System.in);
        initController();
    }

    private void initController() {
        bookingController = new BookingController(new BookingService(CollectionBookingDAO.instanceOf()));
        flightController = new FlightController(new FlightService(CollectionFlightDAO.instanceOf()));
        usersController = new UsersController(new UsersService(CollectionUsersDAO.instanceOf()));
        usersController.loadData();
        flightController.loadData();
        bookingController.loadData();
    }

    private void printCommand(List<OperationApp> command) {
        for (int i = 0; i < command.size(); i++) {
            System.out.println(i + 1 + " - " + command.get(i).operationName);
        }
        System.out.print("Print command -> ");
    }

    private void logIn() {
        System.out.print("Введите логгин -> ");
        String login = scanner.next();
        System.out.print("Введите пароль -> ");
        String password = scanner.next();
        if (usersController.logIn(login, password)) {
            custom.addAll(inSession);
            custom.add(closeSession);
            custom.remove(logInOrRegistration);
            currentUser = usersController.getUserByLogin(login);
            isExit = false;
        } else {
            System.out.println("Ошибка. Логин или пароль введет неправильно");
        }
    }

    private void registration() {
        System.out.print("Введите логгин -> ");
        String login = scanner.next();
        System.out.print("Введите пароль -> ");
        String password = scanner.next();
        if (usersController.registration(login, password)) {
            System.out.println("Регестрация успешна");
        } else {
            System.out.println("Ошибка. Логин уже существует");
        }
    }

    private void printOnlineTable() {
        for (Flight flight : flightController.getAllFlightsInAllDay()) {
            System.out.println(flight.prettyFormat());
        }
    }
}
