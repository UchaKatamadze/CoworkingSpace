public class CoworkingSpace {
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();
        system.start();
    }

    public void start (){
        boolean exit = false;

        showWelcomeMessage();

        while(!exit){
            showMainMenu();

            int options = getIntInput();

            switch (options){
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin():
                    break;
                case 3:
                    exit=true;
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Please try again");

            }
        }
    }
}
