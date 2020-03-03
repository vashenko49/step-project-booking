package booking;

import java.util.List;

public interface BookingDAO {
    public List<Booking> getAllBookingsByLoginUser(String loginUser);

    public Booking getBookingByLoginUserAndFlightId(String loginUser, int flightId);

    public boolean deleteBookingByLoginUserAndFlightId(String loginUser, int flightId);

    public boolean deleteBooking(Booking booking);

    public boolean createBookingByLoginUserAndFlightId(String loginUser, int flightId);

    public boolean addBookingFlight(Booking booking);

}
