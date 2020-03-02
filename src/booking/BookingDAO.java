package booking;

import java.util.List;

public interface BookingDAO {
    public List<Booking> getAllBookingsByUserId(String userId);

    public Booking getBookingByUserIdAndFlightId(String userId, String flightId);

    public boolean deleteBookingByUserIdAndFlightId(String userId, String flightId);

    public boolean createBookingByUserIdAndFlightId(String userId, String flightId);

}
