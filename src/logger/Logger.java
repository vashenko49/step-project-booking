package logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class Logger {

    private static final String err = "[ERROR]";
    private static final String info = "[DEBUG]";

    private static void recordLog(String status, String desc) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("-----------------------------------------------------------------------------------\n");

        LocalDateTime localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), TimeZone.getDefault().toZoneId());
        stringBuilder.append(localDate.getDayOfMonth());
        stringBuilder.append("/");
        stringBuilder.append(localDate.getMonthValue());
        stringBuilder.append("/");
        stringBuilder.append(localDate.getYear());
        stringBuilder.append(" ");
        stringBuilder.append(localDate.getHour());
        stringBuilder.append(":");
        stringBuilder.append(localDate.getMinute());
        stringBuilder.append(" ");
        stringBuilder.append(status);
        stringBuilder.append("\n");
        stringBuilder.append(desc);
        stringBuilder.append("\n");
        stringBuilder.append("-----------------------------------------------------------------------------------\n");

        File file = new File("application.log");

        try (
                FileWriter fileWriter = new FileWriter(file, true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.append(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void info(String desc) { recordLog(info, desc); }

    public static void error(String desc) { recordLog(err, desc); }
}
