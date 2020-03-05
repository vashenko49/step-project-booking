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

    public Booking getBookingByBookingId( int bookingId) {
        return bookingService.getBookingByBookingId( bookingId);
    }

    public boolean deleteBookingByIdBooking(int idBooking) {
        return bookingService.deleteBookingByIdBooking(idBooking);
    }

    public boolean deleteBooking(Booking booking) {
        return bookingService.deleteBooking(booking);
    }

    public boolean createBookingByLoginUserAndFlightId(String loginUser, int flightId, String namePassenger, String lastNamePassenger) {
        return bookingService.createBookingByLoginUserAndFlightId(loginUser, flightId, namePassenger, lastNamePassenger);
    }

    public boolean addBookingFlight(Booking booking) {
        return bookingService.addBookingFlight(booking);
    }
}
