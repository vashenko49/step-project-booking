package flight;

import java.util.List;

public interface FlightDAO {
    public List<Flight> getAllFlights();

    public List<Flight> findFlights(String from, String to, long departmentTime, int numberOfFreePlace);

    public Flight getFlightBuFlightId(int flightId);

    public List<Flight> getAllFlightsInAllDay();

    public boolean createFlight(Flight flight);

    public boolean deleteFlight(Flight flight);
}
