package pl.kolak.bookhotelroom.exceptions;

public class CustomerDoNotAddedException extends RuntimeException {
    public CustomerDoNotAddedException() {
        super("Customer does not add");
    }
}
