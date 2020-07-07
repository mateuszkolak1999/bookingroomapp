package pl.kolak.bookhotelroom.exceptions;

public class BookingDoNotDeletedException extends RuntimeException{
    public BookingDoNotDeletedException() {
        super("Booking does not delete. Id does not find.");
    }
}
