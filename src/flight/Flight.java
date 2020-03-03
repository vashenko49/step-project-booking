package flight;

import java.io.Serializable;
import java.util.Objects;

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
