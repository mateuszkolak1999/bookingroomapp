package pl.kolak.bookhotelroom.exceptions;

public class BookingDoNotCreatedException extends RuntimeException {
    public BookingDoNotCreatedException() {
        super("Booking doesn't create");
    }
}
