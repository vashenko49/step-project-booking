package flight;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.TimeZone;

public class Flight implements Serializable {
    private String from;
    private String to;
    private int idFlight;
    private long departureTime;
    private long arrivalTime;
    private int numberOfFreePlaces;

    public Flight(String from, String to, int idFlight, long departureTime, long arrivalTime, int numberOfFreePlaces) {
        this.from = from;
        this.to = to;
        this.idFlight = idFlight;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.numberOfFreePlaces = numberOfFreePlaces;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public long getDepartureTime() {
        return departureTime;
    }

    public long getArrivalTime() {
        return arrivalTime;
    }

    public int getNumberOfFreePlaces() {
        return numberOfFreePlaces;
    }

    private String getPrettyFormatDate(long time) {
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), TimeZone.getDefault().toZoneId());
        return String.format("%d/%d/%d %d:%d", localDateTime.getDayOfMonth(), localDateTime.getMonthValue(), localDateTime.getYear(), localDateTime.getHour(), localDateTime.getMinute());

    }

    public void bookingFlight(){
        numberOfFreePlaces--;
    }

    public void cancelBookedFlight(){
        numberOfFreePlaces++;
    }

    public String prettyFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("------------------------------------------\n");
        stringBuilder.append("From: ");
        stringBuilder.append(from);
        stringBuilder.append("\n");
        stringBuilder.append("To: ");
        stringBuilder.append(to);
        stringBuilder.append("\n");
        stringBuilder.append("Departure time: ");
        stringBuilder.append(getPrettyFormatDate(departureTime));
        stringBuilder.append("\n");
        stringBuilder.append("Arrival time: ");
        stringBuilder.append(getPrettyFormatDate(arrivalTime));
        stringBuilder.append("\n");
        stringBuilder.append("Travel time: ");

        LocalDateTime to = LocalDateTime.ofInstant(Instant.ofEpochMilli(arrivalTime), TimeZone.getDefault().toZoneId());
        LocalDateTime from = LocalDateTime.from(LocalDateTime.ofInstant(Instant.ofEpochMilli(departureTime), TimeZone.getDefault().toZoneId()));
        long years = from.until(to, ChronoUnit.YEARS);
        from = from.plusYears(years);

        long months = from.until(to, ChronoUnit.MONTHS);
        from = from.plusMonths(months);

        long days = from.until(to, ChronoUnit.DAYS);
        from = from.plusDays(days);

        long hours = from.until(to, ChronoUnit.HOURS);
        from = from.plusHours(hours);

        long minutes = from.until(to, ChronoUnit.MINUTES);

        if (days > 0) {
            stringBuilder.append(days);
            stringBuilder.append(" days ");
        }
        if (hours > 0) {
            stringBuilder.append(hours);
            stringBuilder.append(" hours ");
        }
        stringBuilder.append(minutes);
        stringBuilder.append(" minutes ");
        stringBuilder.append("\n");

        stringBuilder.append("Number of free places: ");
        stringBuilder.append(numberOfFreePlaces);
        stringBuilder.append("\n");
        stringBuilder.append("Id flight: ");
        stringBuilder.append(idFlight);
        stringBuilder.append("\n");
        stringBuilder.append("------------------------------------------\n");
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return String.format("Flight{from='%s', to='%s', idFlight=%d, departureTime=%s, arrivalTime=%s, numberOfFreePlaces=%d}", from, to, idFlight, getPrettyFormatDate(departureTime), getPrettyFormatDate(arrivalTime), numberOfFreePlaces);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return idFlight == flight.idFlight &&
                departureTime == flight.departureTime &&
                arrivalTime == flight.arrivalTime &&
                numberOfFreePlaces == flight.numberOfFreePlaces &&
                Objects.equals(from, flight.from) &&
                Objects.equals(to, flight.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to, idFlight, departureTime, arrivalTime, numberOfFreePlaces);
    }
}
