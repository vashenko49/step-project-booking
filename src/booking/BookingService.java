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
            Logger.info("Запись Bookings в файл");
            return collectionBookingDAO.saveDataToFile();
        } catch (BookingOverflowException ex) {
            Logger.error(ex.toString() + " BookingOverflowException");
            return false;
        }
    }

    public void loadData() {
        try {
            collectionBookingDAO.loadData();
            Logger.info("Восстановление Bookings из файла");
        } catch (BookingOverflowException ex) {
            Logger.error(ex.toString() + " BookingOverflowException");
        }
    }

    public List<Booking> getAllBookingsByLoginUser(String loginUser) {
        Logger.info("Получить брони по логину пользователя");
        return collectionBookingDAO.getAllBookingsByLoginUser(loginUser);
    }

    public Booking getBookingByBookingId(int booingId) {
        Logger.info("Получить брони по логину пользователя и id рейса");
        return collectionBookingDAO.getBookingByBookingId( booingId);
    }

    public boolean deleteBookingByIdBooking( int idBooking) {
        Logger.info("Удалить бронь");
        return collectionBookingDAO.deleteBookingByIdBooking( idBooking);
    }

    public boolean deleteBooking(Booking booking) {
        Logger.info("Удалить бронь");
        return collectionBookingDAO.deleteBooking(booking);
    }

    public boolean createBookingByLoginUserAndFlightId(String loginUser, int flightId, String namePassenger, String lastNamePassenger ) {
        Logger.info("Создать бронь");
        return collectionBookingDAO.createBookingByLoginUserAndFlightId(loginUser, flightId, namePassenger, lastNamePassenger);
    }

    public boolean addBookingFlight(Booking booking) {
        Logger.info("Добавить бронь");
        return collectionBookingDAO.addBookingFlight(booking);
    }
}
