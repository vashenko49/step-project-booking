package booking;


import logger.Logger;

import java.util.List;

public class BookingService {
    private final CollectionBookingDAO collectionBookingDAO;

    public BookingService(CollectionBookingDAO collectionBookingDAO) {
        this.collectionBookingDAO = collectionBookingDAO;
    }

    public boolean saveDataToFile() {
        try {
            return collectionBookingDAO.saveDataToFile();
        } catch (BookingOverflowException ex) {
            Logger.error(ex.toString() + " BookingOverflowException");
            return false;
        }
    }

    public void loadData() {
        try {
            collectionBookingDAO.loadData();
        } catch (BookingOverflowException ex) {
            Logger.error(ex.toString() + " BookingOverflowException");
        }
    }

    public List<Booking> getAllBookingsByLoginUser(String loginUser) {
        return collectionBookingDAO.getAllBookingsByLoginUser(loginUser);
    }

    public Booking getBookingByLoginUserAndFlightId(String loginUser, int flightId) {
        return collectionBookingDAO.getBookingByLoginUserAndFlightId(loginUser, flightId);
    }

    public boolean deleteBookingByLoginUserAndFlightId(String loginUser, int flightId) {
        return collectionBookingDAO.deleteBookingByLoginUserAndFlightId(loginUser, flightId);
    }

    public boolean deleteBooking(Booking booking) {
        return collectionBookingDAO.deleteBooking(booking);
    }

    public boolean createBookingByLoginUserAndFlightId(String loginUser, int flightId) {
        return collectionBookingDAO.createBookingByLoginUserAndFlightId(loginUser, flightId);
    }

    public boolean addBookingFlight(Booking booking) {
        return collectionBookingDAO.addBookingFlight(booking);
    }
}
