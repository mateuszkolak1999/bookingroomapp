package pl.kolak.bookhotelroom.exceptions;

public class RoleDoNotAddedException extends RuntimeException {
    public RoleDoNotAddedException() {
        super("Role does not add");
    }
}
