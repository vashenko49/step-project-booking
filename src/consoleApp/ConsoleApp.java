package consoleApp;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConsoleApp {
    private BookingController bookingController;
    private FlightController flightController;
    private UsersController usersController;

    private boolean isAdmin = false;
    private boolean isLogIn = false;
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
            new OperationApp("Отменить бронирование", this::cancelFlight),
            new OperationApp("Завершшить сессию", this::logOut),
            new OperationApp("Мои бронирования", this::printOnlineTable)
    ));

    private final List<OperationApp> admin = new ArrayList<>(Arrays.asList(
            new OperationApp("Создать рейс", this::createFlight),
            new OperationApp("Удалить рейс", this::deleteFlight),
            new OperationApp("Список клиентов", this::getAllUsers),
            new OperationApp("Сохранить изменения", this::saveChange)
    ));

    private void saveChange() {
        if (usersController.saveDataToFile() && flightController.saveDataToFile() && bookingController.saveDataToFile()) {
            System.out.println("Успешно сохранено");
        } else {
            System.out.println("Ошибка при сохранении");
        }

    }

    private void registrationAndLogInProcedure() {
        isExit = true;
        while (isExit) {
            printCommand(logInRegister);
            OperationApp operationApp = logInRegister.get(Validation.scanInteger(1, logInRegister.size()) - 1);
            System.out.println(operationApp.operationName);
            operationApp.operation.operation();
        }
    }

    public void run() {
        registrationAndLogInProcedure();
        isExit = true;
        List<OperationApp> custom = new ArrayList<>(mainCommands);
        if (isLogIn || isAdmin) {
            custom.addAll(inSession);
        }
        if (isAdmin) {
            custom.addAll(admin);
        }
        while (isExit) {
            printCommand(custom);
            OperationApp operationApp = custom.get(Validation.scanInteger(1, custom.size()) - 1);
            System.out.println(operationApp.operationName);
            operationApp.operation.operation();
        }
    }

    private void exit() {
        isExit = false;
    }

    private void getAllUsers() {
    }

    private void deleteFlight() {
    }

    private void createFlight() {
    }


    private void cancelFlight() {
    }

    private void findAndBookingFlight() {
    }

    private void informationAboutFlight() {
    }


    private void logOut() {
    }

    public ConsoleApp() {
        this.isAdmin = false;
        initController();
    }

    public ConsoleApp(boolean isAdmin) {
        this.isAdmin = isAdmin;
        initController();
    }

    private void initController() {
        bookingController = new BookingController(new BookingService(CollectionBookingDAO.instanceOf()));
        bookingController.loadData();
        flightController = new FlightController(new FlightService(CollectionFlightDAO.instanceOf()));
        flightController.loadData();
        usersController = new UsersController(new UsersService(CollectionUsersDAO.instanceOf()));
        usersController.loadData();
    }

    private void printCommand(List<OperationApp> command) {
        for (int i = 0; i < command.size(); i++) {
            System.out.println(i + 1 + " - " + command.get(i).operationName);
        }
        System.out.print("Print command -> ");
    }

    private void logIn() {

    }

    private void registration() {

    }

    private void printOnlineTable() {

    }
}
