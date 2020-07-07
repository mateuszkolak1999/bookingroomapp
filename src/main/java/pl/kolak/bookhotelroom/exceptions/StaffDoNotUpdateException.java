package pl.kolak.bookhotelroom.exceptions;

public class StaffDoNotUpdateException extends RuntimeException {
    public StaffDoNotUpdateException() {
        super("Staff does not update.");
    }
}
