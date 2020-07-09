package pl.kolak.bookhotelroom.exceptions;

public class RoomIsNotAvailableException extends RuntimeException{
    public RoomIsNotAvailableException() {
        super("Room is not available");
    }
}
