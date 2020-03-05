package booking;

import org.junit.Test;


public class BookingTest {
    @Test
    public void testCreatedObject(){
        //given
        int idFlight = 55;
        String login = "3";
        //when
        Booking newBooking = new Booking(idFlight, 55, "gogo","bobo", "nono");
        //than
    }
}