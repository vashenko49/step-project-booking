package flight;

import service.fileSystem.FileSystemToList;
import service.fileSystem.WorkWithFileSystem;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

public final class CollectionFlightDAO implements FlightDAO, WorkWithFileSystem {
    private static final CollectionFlightDAO COLLECTION_FLIGHT_DAO = new CollectionFlightDAO();
    private final List<Flight> flights = new ArrayList<>();
    private final String fileName = "flights";

    private CollectionFlightDAO() {
    }

    public static CollectionFlightDAO instanceOf() {
        return COLLECTION_FLIGHT_DAO;
    }

    @Override
    public boolean saveDataToFile() throws FlightOverflowException {
        FileSystemToList<Flight> bookingFileSystem = new FileSystemToList<>();
        try {
            return bookingFileSystem.recordListToFile(fileName, flights);
        } catch (IOException e) {
            throw new FlightOverflowException("Ошибка сохранения");
        }
    }

    @Override
    public void loadData() throws FlightOverflowException {
        FileSystemToList<Flight> flightFileSystemToList = new FileSystemToList<>();
        try {
            List<Flight> listFromFile = flightFileSystemToList.getListFromFile(fileName);
            for (Flight flight : listFromFile) {
                if (!flights.contains(flight)) {
                    flights.add(flight);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new FlightOverflowException("Ошибка загрузки");
        }
    }

    @Override
    public List<Flight> getAllFlights() {
        return flights;
    }

    @Override
    public List<Flight> findFlights(String from, String to, long departmentTime, int numberOfFreePlace) {
        LocalDateTime departmentTimeLocalDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(departmentTime), TimeZone.getDefault().toZoneId());
        LocalDateTime tomorrowLocalDateTime = departmentTimeLocalDateTime.plusDays(1);
        return flights.stream()
                .filter(f ->
                        f.getDepartureTime() > departmentTime
                                && f.getDepartureTime() < tomorrowLocalDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
                                && f.getFrom().toLowerCase().equals(from.toLowerCase())
                                && f.getTo().toLowerCase().equals(to.toLowerCase())
                                && f.getNumberOfFreePlaces() > 0
                                && f.getNumberOfFreePlaces() >= numberOfFreePlace)
                .collect(Collectors.toList());
    }

    @Override
    public Flight getFlightBuFlightId(int flightId) {
        for (Flight flight : flights) {
            if (flight.getIdFlight() == flightId) {
                return flight;
            }
        }
        return null;
    }

    @Override
    public List<Flight> getAllFlightsInAllDay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        return flights.stream().filter(f -> f.getDepartureTime() >= now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() && f.getDepartureTime() <= tomorrow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()).collect(Collectors.toList());
    }

    @Override
    public void bookingFlight(int flightId) {
        Flight flightBuFlightId = getFlightBuFlightId(flightId);
        if (Objects.nonNull(flightBuFlightId)) {
            flightBuFlightId.bookingFlight();
            try {
                saveDataToFile();
            } catch (FlightOverflowException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void cancelBookingFlight(int flightId) {
        Flight flightBuFlightId = getFlightBuFlightId(flightId);
        if (Objects.nonNull(flightBuFlightId)) {
            flightBuFlightId.cancelBookedFlight();
            try {
                saveDataToFile();
            } catch (FlightOverflowException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean createFlight(Flight flight) {
        return flights.add(flight);
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        return flights.remove(flight);
    }
}
