public class CoworkingSpace {
    public static void main(String[] args) {
        try {
            ReservationSystem system = new ReservationSystem();
            system.start();
        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
        }

    }

    }
