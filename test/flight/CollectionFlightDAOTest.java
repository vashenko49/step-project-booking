package flight;


import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

public class CollectionFlightDAOTest {
    CollectionFlightDAO collectionFlightDAO = CollectionFlightDAO.instanceOf();

    @Test
    public void addNewFlight() {
        //given
        String from = "FF";
        String to = "NA";
        int idFlight = 4;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        long departureTime = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long arrivalTime = tomorrow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        int numberOfFreePlaces = 3;
        Flight flight = new Flight(from, to, idFlight, departureTime, arrivalTime, numberOfFreePlaces);
        //when
        collectionFlightDAO.createFlight(flight);
        //than
        Assert.assertTrue(Objects.nonNull(collectionFlightDAO.getFlightBuFlightId(idFlight)));
        collectionFlightDAO.deleteFlight(flight);
    }


    @Test
    public void bookingFlight() {
        //given
        String from = "FF";
        String to = "NA";
        int idFlight = 4;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        long departureTime = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long arrivalTime = tomorrow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        int numberOfFreePlaces = 3;
        Flight flight = new Flight(from, to, idFlight, departureTime, arrivalTime, numberOfFreePlaces);
        collectionFlightDAO.createFlight(flight);
        //when
        int expect = collectionFlightDAO.getFlightBuFlightId(idFlight).getNumberOfFreePlaces() - 1;
        collectionFlightDAO.bookingFlight(idFlight);
        int actual = collectionFlightDAO.getFlightBuFlightId(idFlight).getNumberOfFreePlaces();
        //than
        Assert.assertEquals(expect, actual);
    }

    @Test
    public void cancelBookingFlight() {
        //given
        String from = "FF";
        String to = "NA";
        int idFlight = 4;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        long departureTime = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long arrivalTime = tomorrow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        int numberOfFreePlaces = 3;
        Flight flight = new Flight(from, to, idFlight, departureTime, arrivalTime, numberOfFreePlaces);
        collectionFlightDAO.createFlight(flight);
        //when
        int expect = collectionFlightDAO.getFlightBuFlightId(idFlight).getNumberOfFreePlaces();
        collectionFlightDAO.bookingFlight(idFlight);
        collectionFlightDAO.cancelBookingFlight(idFlight);
        int actual = collectionFlightDAO.getFlightBuFlightId(idFlight).getNumberOfFreePlaces();
        //than
        Assert.assertEquals(expect, actual);
    }
}