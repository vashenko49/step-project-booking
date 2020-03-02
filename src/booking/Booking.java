package booking;

import java.io.Serializable;

public class Booking implements Serializable {
    private int idFlight;
    private int idUser;

    public Booking(int idFlight, int idUser) {
        this.idFlight = idFlight;
        this.idUser = idUser;
    }
}
