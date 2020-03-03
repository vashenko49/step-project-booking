package Service.valid;

import java.util.Scanner;

public class Validation {
    private final static Scanner scanner = new Scanner(System.in);

    public static boolean isNumeric(String strNum, int from, int to) {
        return isNumeric(strNum) && Integer.parseInt(strNum) >= from && Integer.parseInt(strNum) <= to;
    }

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    public int scanInteger() {
        String integer = scanner.next();
        boolean isValid = isNumeric(integer);
        while (!isValid) {
            System.out.println("Enter valid data -> ");
            integer = scanner.next();
            isValid = isNumeric(integer);
        }

        return Integer.parseInt(integer);
    }

    public int scanInteger(int from, int to) {
        String integer = scanner.next();
        boolean isValid = isNumeric(integer, from, to);
        while (!isValid) {
            System.out.print("Enter valid data -> ");
            integer = scanner.next();
            isValid = isNumeric(integer);
        }

        return Integer.parseInt(integer);
    }
}
