package pl.kolak.bookhotelroom.exceptions;

public class RoomDoNotUpdateException extends RuntimeException {
    public RoomDoNotUpdateException() {
        super("Room does not update");
    }
}
