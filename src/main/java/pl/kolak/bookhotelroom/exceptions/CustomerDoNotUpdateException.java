package pl.kolak.bookhotelroom.exceptions;

public class CustomerDoNotUpdateException extends RuntimeException {
    public CustomerDoNotUpdateException() {
        super("Customer does not update");
    }
}
