package flight;

import java.io.Serializable;

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
}
