package pl.kolak.bookhotelroom.exceptions;

public class EquipmentDoNotUpdateException extends RuntimeException {
    public EquipmentDoNotUpdateException() {
        super("Equipment does not update.");
    }
}
