package booking;

import service.fileSystem.FileSystemToList;
import service.fileSystem.WorkWithFileSystem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public final class CollectionBookingDAO implements BookingDAO, WorkWithFileSystem {
    private static final CollectionBookingDAO COLLECTION_BOOKING_DAO = new CollectionBookingDAO();
    private List<Booking> bookings = new ArrayList<>();
    private final String fileName = "bookings";


    private CollectionBookingDAO() {
    }

    public static CollectionBookingDAO instanceOf() {
        return COLLECTION_BOOKING_DAO;
    }

    @Override
    public boolean saveDataToFile() throws BookingOverflowException {
        FileSystemToList<Booking> bookingFileSystem = new FileSystemToList<>();
        try {
            return bookingFileSystem.recordListToFile(fileName, bookings);
        } catch (IOException e) {
            throw new BookingOverflowException("Ошибка сохранения");
        }
    }

    @Override
    public void loadData() throws BookingOverflowException {
        FileSystemToList<Booking> bookingFileSystem = new FileSystemToList<>();
        try {
            List<Booking> bookingOld = bookingFileSystem.getListFromFile(fileName);
            for (Booking booking : bookingOld) {
                if (!bookings.contains(booking)) {
                    bookings.add(booking);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new BookingOverflowException("Ошибка загрузки");
        }
    }

    @Override
    public List<Booking> getAllBookingsByLoginUser(String loginUser) {
        return bookings.stream().filter(b -> b.getLoginUser().equals(loginUser)).collect(Collectors.toList());
    }

    @Override
    public Booking getBookingByBookingId( int idBooking) {
        for (Booking booking : bookings) {
            if (booking.getIdBooking() == idBooking) {
                return booking;
            }
        }
        return null;
    }

    @Override
    public boolean deleteBookingByIdBooking(int idBooking) {
        boolean result = bookings.removeIf(booking -> booking.getIdBooking() == idBooking);
        if (result) {
            try {
                saveDataToFile();
            } catch (BookingOverflowException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean deleteBooking(Booking booking) {
        boolean result = bookings.remove(booking);
        if (result) {
            try {
                saveDataToFile();
            } catch (BookingOverflowException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public boolean createBookingByLoginUserAndFlightId(String loginUser, int flightId, String namePassenger, String lastNamePassenger) {
        Booking booking = new Booking(flightId, ThreadLocalRandom.current().nextInt(100, 200), loginUser, namePassenger, lastNamePassenger);
        bookings.add(booking);
        try {
            saveDataToFile();
        } catch (BookingOverflowException e) {
            e.printStackTrace();
        }

        return bookings.contains(booking);
    }

    @Override
    public boolean addBookingFlight(Booking booking) {
        bookings.add(booking);
        try {
            saveDataToFile();
        } catch (BookingOverflowException e) {
            e.printStackTrace();
        }
        return bookings.contains(booking);
    }
}
