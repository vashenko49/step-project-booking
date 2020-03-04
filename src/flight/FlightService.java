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
            Logger.info("Запись flights в файл");
            return COLLECTION_FLIGHT_DAO.saveDataToFile();
        } catch (FlightOverflowException ex) {
            Logger.error(ex.toString() + " FlightOverflowException");
            return false;
        }
    }


    public void loadData() {
        try {
            COLLECTION_FLIGHT_DAO.loadData();
            Logger.info("Восстановление flights из файла");
        } catch (FlightOverflowException ex) {
            Logger.error(ex.toString() + " FlightOverflowException");
        }
    }


    public List<Flight> getAllFlights() {
        Logger.info("Получить список всех рейсов");
        return COLLECTION_FLIGHT_DAO.getAllFlights();
    }


    public Flight getFlightBuFlightId(int flightId) {
        Logger.info("Получить рейс по id");
        return COLLECTION_FLIGHT_DAO.getFlightBuFlightId(flightId);
    }


    public List<Flight> getAllFlightsInAllDay() {
        Logger.info("Получить рейсы на 24 часа");
        return COLLECTION_FLIGHT_DAO.getAllFlightsInAllDay();
    }


    public boolean createFlight(Flight flight) {
        Logger.info("Создание рейса");
        return COLLECTION_FLIGHT_DAO.createFlight(flight);
    }


    public boolean deleteFlight(Flight flight) {
        Logger.info("Удаление рейса");
        return COLLECTION_FLIGHT_DAO.deleteFlight(flight);
    }

    public List<Flight> findFlights(String from, String to, long departmentTime, int numberOfFreePlace) {
        Logger.info("Поиск рейса");
        return COLLECTION_FLIGHT_DAO.findFlights(from, to, departmentTime,numberOfFreePlace);
    }
}
