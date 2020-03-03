package booking;

import java.io.Serializable;
import java.util.Objects;

public class Booking implements Serializable {
    private int idFlight;
    private String loginUser;


    public int getIdFlight() {
        return idFlight;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public Booking(int idFlight, String loginUser) {
        this.idFlight = idFlight;
        this.loginUser = loginUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return idFlight == booking.idFlight &&
                Objects.equals(loginUser, booking.loginUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFlight, loginUser);
    }
}
