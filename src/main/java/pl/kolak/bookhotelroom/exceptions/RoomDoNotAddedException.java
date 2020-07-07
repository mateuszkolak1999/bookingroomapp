package pl.kolak.bookhotelroom.exceptions;

public class RoomDoNotAddedException extends RuntimeException {
    public RoomDoNotAddedException() {
        super("Room does not add");
    }
}
