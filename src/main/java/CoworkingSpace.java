import java.util.Scanner;


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

        scanner.close();
    }

    private void showWelcomeMessage(){
        System.out.println("Welcome");
    }

    private void showMainMenu(){
        System.out.println("\nMain Menu:");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. Exit");
        System.out.print("Please choose: ");
    }

    private void adminLogin(){
        System.out.println("Admin password:");
        String password = scanner.nextLIne();
        currentUser = new Customer(name);
        showCustomerMenu();
    }

    private void showAdminMenu(){
        boolean back = false;

        while(!back){
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add a new coworking space");
            System.out.println("2. Remove a coworking space");
            System.out.println("3. View all reservations");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
        }
    }
}
