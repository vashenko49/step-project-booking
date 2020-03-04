package booking;

import java.util.List;

public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public boolean saveDataToFile() {
        return bookingService.saveDataToFile();
    }

    public void loadData() {
        bookingService.loadData();
    }

    public List<Booking> getAllBookingsByLoginUser(String loginUser) {
        return bookingService.getAllBookingsByLoginUser(loginUser);
    }

    public Booking getBookingByLoginUserAndFlightId(String loginUser, int flightId) {
        return bookingService.getBookingByLoginUserAndFlightId(loginUser, flightId);
    }

    public boolean deleteBookingByLoginUserAndDdBooking(String loginUser, int idBooking, String namePassenger) {
        return bookingService.deleteBookingByLoginUserAndDdBooking(loginUser, idBooking, namePassenger);
    }

    public boolean deleteBooking(Booking booking) {
        return bookingService.deleteBooking(booking);
    }

    public boolean createBookingByLoginUserAndFlightId(String loginUser, int flightId, String namePassenger) {
        return bookingService.createBookingByLoginUserAndFlightId(loginUser, flightId, namePassenger);
    }

    public boolean addBookingFlight(Booking booking) {
        return bookingService.addBookingFlight(booking);
    }
}
