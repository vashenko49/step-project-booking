package booking;

import java.io.Serializable;
import java.util.Objects;

public class Booking implements Serializable {
    private int idFlight;
    private int idBooking;
    private String loginUser;
    private String namePassenger;
    private String lastNamePassenger;

    public String getLastNamePassenger() {
        return lastNamePassenger;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public String getNamePassenger() {
        return namePassenger;
    }

    public int getIdBooking() {
        return idBooking;
    }

    public int getIdFlight() {
        return idFlight;
    }

    public Booking(int idFlight, int idBooking, String loginUser, String namePassenger, String lastNamePassenger) {
        this.idFlight = idFlight;
        this.idBooking = idBooking;
        this.loginUser = loginUser;
        this.namePassenger = namePassenger;
        this.lastNamePassenger = lastNamePassenger;
    }

    public String prettyFormat() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Id booking: ");
        stringBuilder.append(idBooking);
        stringBuilder.append("\n");
        stringBuilder.append("Id flight: ");
        stringBuilder.append(idFlight);
        stringBuilder.append("\n");
        stringBuilder.append("Name passenger: ");
        stringBuilder.append(namePassenger);
        stringBuilder.append("\n");
        stringBuilder.append("Last name passenger: ");
        stringBuilder.append(lastNamePassenger);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return idFlight == booking.idFlight &&
                idBooking == booking.idBooking &&
                Objects.equals(loginUser, booking.loginUser) &&
                Objects.equals(namePassenger, booking.namePassenger) &&
                Objects.equals(lastNamePassenger, booking.lastNamePassenger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFlight, idBooking, loginUser, namePassenger, lastNamePassenger);
    }
}
