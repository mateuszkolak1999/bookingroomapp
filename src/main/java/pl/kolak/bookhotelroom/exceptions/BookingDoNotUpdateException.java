package pl.kolak.bookhotelroom.exceptions;

public class BookingDoNotUpdateException extends RuntimeException {
    public BookingDoNotUpdateException() {
        super("Booking does not update");
    }
}
