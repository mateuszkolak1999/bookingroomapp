package pl.kolak.bookhotelroom.exceptions;

public class EquipmentDoNotAddedException extends RuntimeException {
    public EquipmentDoNotAddedException() {
        super("Equipment does not add.");
    }
}
