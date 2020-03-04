package booking;

import java.util.List;

public interface BookingDAO {
    public List<Booking> getAllBookingsByLoginUser(String loginUser);

    public Booking getBookingByLoginUserAndFlightId(String loginUser, int flightId);

    public boolean deleteBookingByLoginUserAndDdBooking(String loginUser, int idBooking, String namePassenger);

    public boolean deleteBooking(Booking booking);

    public boolean createBookingByLoginUserAndFlightId(String loginUser, int flightId, String namePassenger);

    public boolean addBookingFlight(Booking booking);

}
