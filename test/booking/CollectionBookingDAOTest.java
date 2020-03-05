package booking;


import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class CollectionBookingDAOTest {
    CollectionBookingDAO collectionBookingDAO = CollectionBookingDAO.instanceOf();

    @Test
    public void createBookingByLoginUserAndFlightIdAndCancel() {
        //given
        String loginUser = "123";
        int flightId = 123;
        String namePassenger = "1";
        String lastNamePassenger = "2";
        //when
        boolean createActual = collectionBookingDAO.createBookingByLoginUserAndFlightId(loginUser, flightId, namePassenger, lastNamePassenger);
        List<Booking> bookings = collectionBookingDAO.getAllBookingsByLoginUser(loginUser);
        boolean deleteActual = collectionBookingDAO.deleteBookingByIdBooking(bookings.get(0).getIdBooking());
        //than
        Assert.assertTrue(createActual);
        Assert.assertTrue(deleteActual);
    }

}