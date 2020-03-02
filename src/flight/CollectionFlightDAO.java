package flight;

import Service.fileSystem.WorkWithFileSystem;

import java.util.List;

public class CollectionFlightDAO implements FlightDAO, WorkWithFileSystem {
    public static final CollectionFlightDAO COLLECTION_FLIGHT_DAO = new CollectionFlightDAO();

    private CollectionFlightDAO() {
    }

    public static CollectionFlightDAO instanceOf() {
        return COLLECTION_FLIGHT_DAO;
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
    public List<Flight> getAllFlights() {
        return null;
    }

    @Override
    public Flight getFlightBuFlightId(int flightId) {
        return null;
    }

    @Override
    public List<Flight> getAllFlightsInAllDay() {
        return null;
    }

    @Override
    public boolean createFlight(Flight flight) {
        return false;
    }

    @Override
    public boolean deleteFlight(Flight flight) {
        return false;
    }
}
