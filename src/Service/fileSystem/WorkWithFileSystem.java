package Service.fileSystem;

import booking.BookingOverflowException;
import flight.FlightOverflowException;
import users.UsersOverflowException;

import java.io.IOException;

public interface WorkWithFileSystem {
    public boolean saveDataToFile() throws BookingOverflowException, FlightOverflowException, UsersOverflowException;

    public void loadData() throws IOException, ClassNotFoundException, BookingOverflowException, FlightOverflowException, UsersOverflowException;
}
