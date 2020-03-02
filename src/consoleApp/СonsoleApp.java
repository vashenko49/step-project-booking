package consoleApp;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class СonsoleApp {
    private final Scanner scanner;
    private final boolean isAdmin;
    private final List<OperationApp> mainCommands = Arrays.asList(
            new OperationApp("Онайн-табло", this::printOnlineTable),
            new OperationApp("Посмотреть информацию о рейсе", this::printOnlineTable),
            new OperationApp("Поиск и бронировка рейса", this::printOnlineTable),
            new OperationApp("Отменить бронирование", this::printOnlineTable),
            new OperationApp("Мои бронирования", this::printOnlineTable),
            new OperationApp("Выход", this::printOnlineTable)
    );

    public СonsoleApp() {
        this.scanner = new Scanner(System.in);
        this.isAdmin = false;
    }

    public СonsoleApp(boolean isAdmin) {
        this.scanner = new Scanner(System.in);
        this.isAdmin = isAdmin;
    }

    private void printOnlineTable() {

    }
}
