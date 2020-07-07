package pl.kolak.bookhotelroom.exceptions;

public class RoleDoNotUpdateException extends RuntimeException {
    public RoleDoNotUpdateException() {
        super("Role does not update.");
    }
}
