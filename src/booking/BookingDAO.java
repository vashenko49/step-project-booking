package booking;

import java.util.List;

public interface BookingDAO {
    public List<Booking> getAllBookingsByLoginUser(String loginUser);

    public Booking getBookingByBookingId(int bookingId);

    public boolean deleteBookingByIdBooking(int idBooking);

    public boolean deleteBooking(Booking booking);

    public boolean createBookingByLoginUserAndFlightId(String loginUser, int flightId, String namePassenger, String lastNamePassenger);

    public boolean addBookingFlight(Booking booking);

}
