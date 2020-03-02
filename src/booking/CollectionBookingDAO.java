package booking;

import Service.fileSystem.WorkWithFileSystem;

import java.util.List;

public final class CollectionBookingDAO implements BookingDAO, WorkWithFileSystem {
    public static final CollectionBookingDAO COLLECTION_BOOKING_DAO = new CollectionBookingDAO();

    private CollectionBookingDAO() {
    }

    public static CollectionBookingDAO instanceOf() {
        return COLLECTION_BOOKING_DAO;
    }

    @Override
    public boolean saveDataToFile() {
        return false;
    }

    @Override
    public boolean loadData() {
        return false;
    }

    @Override
    public List<Booking> getAllBookingsByUserId(String userId) {
        return null;
    }

    @Override
    public Booking getBookingByUserIdAndFlightId(String userId, String flightId) {
        return null;
    }

    @Override
    public boolean deleteBookingByUserIdAndFlightId(String userId, String flightId) {
        return false;
    }

    @Override
    public boolean createBookingByUserIdAndFlightId(String userId, String flightId) {
        return false;
    }
}
