package pl.kolak.bookhotelroom.exceptions;

public class StaffDoNotAddedException extends RuntimeException{
    public StaffDoNotAddedException() {
        super("Staff doest't add");
    }
}
