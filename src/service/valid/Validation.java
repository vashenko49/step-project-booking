package service.valid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validation {
    private final static Scanner scanner = new Scanner(System.in);

    public static boolean isNumeric(String strNum, int from, int to) {
        return isNumeric(strNum) && Integer.parseInt(strNum) >= from && Integer.parseInt(strNum) <= to;
    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isDate(String s) {
        return s.matches("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d[- /.](([0,1][0-9])|(2[0-3])):[0-5][0-9]");
    }

    public static int scanInteger() {
        String integer = scanner.next();
        boolean isValid = isNumeric(integer);
        while (!isValid) {
            System.out.println("Enter valid data -> ");
            integer = scanner.next();
            isValid = isNumeric(integer);
        }

        return Integer.parseInt(integer);
    }

    public static long scanDate() {
        String date = scanner.next();
        boolean isValid = isDate(date);
        while (!isValid) {
            System.out.println("Enter valid data -> ");
            date = scanner.next();
            isValid = isDate(date);
        }

        return StringDateToLongDate(date);
    }

    public static int scanInteger(int from, int to) {
        String integer = scanner.next();
        boolean isValid = isNumeric(integer, from, to);
        while (!isValid) {
            System.out.print("Enter valid data -> ");
            integer = scanner.next();
            isValid = isNumeric(integer, from, to);
        }

        return Integer.parseInt(integer);
    }

    private static long StringDateToLongDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
}
