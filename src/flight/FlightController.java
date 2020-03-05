package flight;


import java.util.List;

public class FlightController {
    private final FlightService FLIGHT_SERVICE;

    public FlightController(FlightService FLIGHT_SERVICE) {
        this.FLIGHT_SERVICE = FLIGHT_SERVICE;
    }

    public boolean saveDataToFile() {
        return FLIGHT_SERVICE.saveDataToFile();
    }


    public void loadData() {
        FLIGHT_SERVICE.loadData();
    }


    public List<Flight> getAllFlights() {
        return FLIGHT_SERVICE.getAllFlights();
    }


    public Flight getFlightBuFlightId(int flightId) {
        return FLIGHT_SERVICE.getFlightBuFlightId(flightId);
    }


    public List<Flight> getAllFlightsInAllDay() {
        return FLIGHT_SERVICE.getAllFlightsInAllDay();
    }


    public boolean createFlight(Flight flight) {
        return FLIGHT_SERVICE.createFlight(flight);
    }


    public boolean deleteFlight(Flight flight) {
        return FLIGHT_SERVICE.deleteFlight(flight);
    }

    public List<Flight> findFlights(String from, String to, long departmentTime, int numberOfFreePlace) {
        return FLIGHT_SERVICE.findFlights(from, to, departmentTime, numberOfFreePlace);
    }

    public void bookingFlight(int flightId) {
        FLIGHT_SERVICE.bookingFlight(flightId);
    }

    public void cancelBookingFlight(int flightId) {
        FLIGHT_SERVICE.cancelBookingFlight(flightId);
    }
}
