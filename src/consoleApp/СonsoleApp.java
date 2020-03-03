package consoleApp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class СonsoleApp {
    private final Scanner scanner;
    private final boolean isAdmin;
    private final List<OperationApp> mainCommands = Arrays.asList(
            new OperationApp("Онайн-табло", this::printOnlineTable),
            new OperationApp("Посмотреть информацию о рейсе", this::informationAboutFlight),
            new OperationApp("Поиск и бронировка рейса", this::findAndBookingFlight),
            new OperationApp("Отменить бронирование", this::cancelFlight),
            new OperationApp("Выход", this::exitFromApp)
    );

    private void exitFromApp() {
    }

    private void cancelFlight() {
    }

    private void findAndBookingFlight() {
    }

    private void informationAboutFlight() {
    }

    private final List<OperationApp> logInRegister = Arrays.asList(
            new OperationApp("Войти", this::logIn),
            new OperationApp("Регестрация", this::registration)
    );

    private final List<OperationApp> inSession = Arrays.asList(
            new OperationApp("Завершшить сессию", this::logOut),
            new OperationApp("Мои бронирования", this::printOnlineTable)
    );

    private void logOut() {
    }

    public СonsoleApp() {
        this.scanner = new Scanner(System.in);
        this.isAdmin = false;
    }

    public СonsoleApp(boolean isAdmin) {
        this.scanner = new Scanner(System.in);
        this.isAdmin = isAdmin;
    }
    
    public void run() {

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
