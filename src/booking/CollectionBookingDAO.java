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
            bookingFileSystem.getListFromFile(fileName).stream().peek(b -> {
                if (!bookings.contains(b)) {
                    bookings.add(b);
                }
            }).close();
        } catch (IOException | ClassNotFoundException e) {
            throw new BookingOverflowException("Ошибка загрузки");
        }
    }

    @Override
    public List<Booking> getAllBookingsByLoginUser(String loginUser) {
        return bookings.stream().filter(b -> b.getLoginUser().equals(loginUser)).collect(Collectors.toList());
    }

    @Override
    public Booking getBookingByLoginUserAndFlightId(String loginUser, int flightId) {
        for (Booking booking : bookings) {
            if (booking.getIdFlight() == flightId && booking.getLoginUser().equals(loginUser)) {
                return booking;
            }
        }
        return null;
    }

    @Override
    public boolean deleteBookingByLoginUserAndDdBooking(String loginUser, int idBooking, String namePassenger) {
        boolean result = bookings.removeIf(booking -> booking.getIdBooking() == idBooking && booking.getLoginUser().equals(loginUser) && booking.getNamePassenger().equals(namePassenger));
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
    public boolean createBookingByLoginUserAndFlightId(String loginUser, int flightId, String namePassenger) {
        boolean result = bookings.add(new Booking(flightId, ThreadLocalRandom.current().nextInt(100, 200), loginUser, namePassenger));
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
    public boolean addBookingFlight(Booking booking) {
        boolean result = bookings.add(booking);
        if (result) {
            try {
                saveDataToFile();
            } catch (BookingOverflowException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
