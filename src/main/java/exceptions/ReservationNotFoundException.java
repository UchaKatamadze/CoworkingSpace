package exceptions;

class ReservationNotFoundException extends Exception {
    public ReservationNotFoundException(int id) {
        super("Reservation not found with ID: " + id);
    }
}
