package flight;

import Service.fileSystem.FileSystemToList;
import Service.fileSystem.WorkWithFileSystem;

import java.io.IOException;
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
        FileSystemToList<Flight> bookingFileSystem = new FileSystemToList<>();
        try {
            bookingFileSystem.getListFromFile(fileName).stream().peek(b -> {
                if (!flights.contains(b)) {
                    flights.add(b);
                }
            }).close();
        } catch (IOException | ClassNotFoundException e) {
            throw new FlightOverflowException("Ошибка загрузки");
        }
    }

    @Override
    public List<Flight> getAllFlights() {
        return flights;
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
        long lastDay = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
        return flights.stream().filter(f -> f.getArrivalTime() > lastDay).collect(Collectors.toList());
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
