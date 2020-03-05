package flight;

public class FlightOverflowException extends Throwable {
    public FlightOverflowException(String dec) {
        super(dec);
    }
}
