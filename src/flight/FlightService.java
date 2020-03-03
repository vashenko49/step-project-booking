package flight;

import logger.Logger;

import java.util.List;


public class FlightService {
    private final CollectionFlightDAO COLLECTION_FLIGHT_DAO;

    public FlightService(CollectionFlightDAO collectionFlightDAO) {
        this.COLLECTION_FLIGHT_DAO = collectionFlightDAO;
    }

    public boolean saveDataToFile() {
        try {
            return COLLECTION_FLIGHT_DAO.saveDataToFile();
        } catch (FlightOverflowException ex) {
            Logger.error(ex.toString() + " FlightOverflowException");
            return false;
        }
    }


    public void loadData() {
        try {
            COLLECTION_FLIGHT_DAO.loadData();
        } catch (FlightOverflowException ex) {
            Logger.error(ex.toString() + " FlightOverflowException");
        }
    }


    public List<Flight> getAllFlights() {
        return COLLECTION_FLIGHT_DAO.getAllFlights();
    }


    public Flight getFlightBuFlightId(int flightId) {
        return COLLECTION_FLIGHT_DAO.getFlightBuFlightId(flightId);
    }


    public List<Flight> getAllFlightsInAllDay() {
        return COLLECTION_FLIGHT_DAO.getAllFlightsInAllDay();
    }


    public boolean createFlight(Flight flight) {
        return COLLECTION_FLIGHT_DAO.createFlight(flight);
    }


    public boolean deleteFlight(Flight flight) {
        return COLLECTION_FLIGHT_DAO.deleteFlight(flight);
    }
}
