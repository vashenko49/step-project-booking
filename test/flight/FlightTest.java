package flight;


import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class FlightTest {
    @Test
    public void testCreateObject() {
        //given
        String from = "FF";
        String to = "NA";
        int idFlight = 4;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        long departureTime = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long arrivalTime = tomorrow.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        int numberOfFreePlaces = 3;
        //when
        Flight flight = new Flight(from, to, idFlight, departureTime, arrivalTime, numberOfFreePlaces);
        //than
    }
}