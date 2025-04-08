import exceptions.DuplicateUserIDException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationSystem {
    private final List<Workspace> workspaces;
    private final List<Reservation> reservations;
    private User currentUser;
    private final DateTimeFormatter date;
    private final Scanner scanner;


    public ReservationSystem() {
        workspaces = new ArrayList<>();
        reservations = new ArrayList<>();
        scanner = new Scanner(System.in);
        date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            addWorkspace(new Workspace(1, "Open space", 10.0, true));
            addWorkspace(new Workspace(2, "Private room", 25.0, true));
            addWorkspace(new Workspace(3, "OMeeting room", 45.0, true));
        } catch (DuplicateUserIDException e) {
            System.out.println("Error loading workspaces" + e.getMessage());

        }

    }

    private void addWorkspace(Workspace workspace) throws DuplicateUserIDException {
        // Check for duplicate workspace ID
        for (Workspace existingWorkspace : workspaces) {
            if (existingWorkspace.getId() == workspace.getId()) {
                throw new DuplicateUserIDException("Workspace with ID " + workspace.getId() + " already exists");
            }
        }
        workspaces.add(workspace);
    }

    public void start() {
        boolean exit = false;

        showWelcomeMessage();

        while (!exit) {
            showMainMenu();

            int options = getIntInput();

            switch (options) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    userLogin();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Please try again");

            }
        }

        scanner.close();
    }

    private void showWelcomeMessage() {
        System.out.println("=============================================");
        System.out.println("    COWORKING SPACE RESERVATION SYSTEM");
        System.out.println("=============================================");
        System.out.println("Welcome to our Coworking Space Reservation System!");
    }

    private void showMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Admin Login");
        System.out.println("2. User Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    private void adminLogin() {
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        // Simple password for demo purposes
        if (password.equals("admin123")) {
            currentUser = new Admin();
            showAdminMenu();
        } else {
            System.out.println("Invalid password. Access denied.");
        }
    }

    private void userLogin() {
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        currentUser = new Customer(name);
        showCustomerMenu();
    }

    private void showAdminMenu() {
        boolean back = false;

        while (!back) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add a new coworking space");
            System.out.println("2. Remove a coworking space");
            System.out.println("3. View all reservations");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addCoworkingSpace();
                    break;
                case 2:
                    removeCoworkingSpace();
                    break;
                case 3:
                    viewAllReservations();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void showCustomerMenu() {
        boolean back = false;
        Customer customer = (Customer) currentUser;

        while (!back) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Browse available spaces");
            System.out.println("2. Make a reservation");
            System.out.println("3. View my reservations");
            System.out.println("4. Cancel a reservation");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    browseSpaces();
                    break;
                case 2:
                    makeReservation(customer);
                    break;
                case 3:
                    viewMyReservations(customer);
                    break;
                case 4:
                    cancelReservation(customer);
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addCoworkingSpace() {
        System.out.println("\nAdd a new coworking space:");

        int id = workspaces.size() + 1;

        System.out.print("Enter space type (Open Space, Private Room, etc.): ");
        String type = scanner.nextLine();

        System.out.print("Enter price per hour: ");
        double price = getDoubleInput();

        workspaces.add(new Workspace(id, type, price, true));
        System.out.println("Coworking space added successfully!");
    }

    private void removeCoworkingSpace() {
        System.out.println("\nRemove a coworking space:");
        browseSpaces();

        System.out.print("Enter ID of space to remove: ");
        int id = getIntInput();

        boolean removed = false;
        for (int i = 0; i < workspaces.size(); i++) {
            if (workspaces.get(i).getId() == id) {
                workspaces.remove(i);
                removed = true;
                System.out.println("Coworking space removed successfully!");
                break;
            }
        }

        if (!removed) {
            System.out.println("No workspace found with that ID.");
        }
    }

    private void browseSpaces() {
        System.out.println("\nAvailable Coworking Spaces:");
        System.out.println("---------------------------------------------");
        System.out.printf("%-5s %-20s %-10s %-15s %n", "ID", "Type", "Price", "Availability");
        System.out.println("---------------------------------------------");

        for (Workspace space : workspaces) {
            System.out.printf("%-5d %-20s $%-9.2f %-15s %n",
                    space.getId(),
                    space.getType(),
                    space.getPrice(),
                    space.isAvailable() ? "Available" : "Not Available");
        }

        System.out.println("---------------------------------------------");
    }

    private void makeReservation(Customer customer) {
        System.out.println("\nMake a Reservation:");
        browseSpaces();

        System.out.print("Enter workspace ID to reserve: ");
        int workspaceId = getIntInput();

        Workspace selectedWorkspace = null;
        for (Workspace space : workspaces) {
            if (space.getId() == workspaceId && space.isAvailable()) {
                selectedWorkspace = space;
                break;
            }
        }

        if (selectedWorkspace == null) {
            System.out.println("Invalid workspace ID or workspace is not available.");
            return;
        }

        System.out.print("Enter start date and time (yyyy-MM-dd HH:mm): ");
        LocalDateTime startTime = getDateTimeInput();
        if (startTime == null) return;

        System.out.print("Enter end date and time (yyyy-MM-dd HH:mm): ");
        LocalDateTime endTime = getDateTimeInput();
        if (endTime == null) return;

        if (endTime.isBefore(startTime)) {
            System.out.println("End time cannot be before start time.");
            return;
        }

        int reservationId = reservations.size() + 1;
        Reservation reservation = new Reservation(
                reservationId,
                customer.getName(),
                selectedWorkspace,
                startTime,
                endTime
        );

        reservations.add(reservation);

        System.out.println("Reservation created successfully!");
        System.out.println("Your reservation ID is: " + reservationId);
    }

    private void viewMyReservations(Customer customer) {
        System.out.println("\nMy Reservations:");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-20s %-20s %n",
                "ID", "Space Type", "Price", "Start Time", "End Time");
        System.out.println("---------------------------------------------------------------------------");

        boolean hasReservations = false;

        for (Reservation res : reservations) {
            if (res.getCustomerName().equals(customer.getName())) {
                System.out.printf("%-5d %-15s $%-14.2f %-20s %-20s %n",
                        res.getId(),
                        res.getWorkspace().getType(),
                        res.getWorkspace().getPrice(),
                        res.getStartTime().format(date),
                        res.getEndTime().format(date));
                hasReservations = true;
            }
        }

        if (!hasReservations) {
            System.out.println("You don't have any reservations yet.");
        }

        System.out.println("---------------------------------------------------------------------------");
    }

    private void cancelReservation(Customer customer) {
        viewMyReservations(customer);

        System.out.print("Enter reservation ID to cancel: ");
        int id = getIntInput();

        boolean cancelled = false;
        for (int i = 0; i < reservations.size(); i++) {
            Reservation reservation = reservations.get(i);
            if (reservation.getId() == id && reservation.getCustomerName().equals(customer.getName())) {
                reservations.remove(i);
                System.out.println("Reservation cancelled successfully!");
                cancelled = true;
                break;
            }
        }

        if (!cancelled) {
            System.out.println("No reservation found with that ID or you don't have permission to cancel it.");
        }
    }

    private void viewAllReservations() {
        System.out.println("\nAll Reservations:");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.printf("%-5s %-15s %-15s %-15s %-20s %-20s %n",
                "ID", "Customer", "Space Type", "Price", "Start Time", "End Time");
        System.out.println("----------------------------------------------------------------------------------------");

        if (reservations.isEmpty()) {
            System.out.println("No reservations in the system yet.");
        } else {
            for (Reservation res : reservations) {
                System.out.printf("%-5d %-15s %-15s $%-14.2f %-20s %-20s %n",
                        res.getId(),
                        res.getCustomerName(),
                        res.getWorkspace().getType(),
                        res.getWorkspace().getPrice(),
                        res.getStartTime().format(date),
                        res.getEndTime().format(date));
            }
        }

        System.out.println("----------------------------------------------------------------------------------------");
    }

    private int getIntInput() {
        String input = scanner.nextLine();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private double getDoubleInput() {
        String input = scanner.nextLine();
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private LocalDateTime getDateTimeInput() {
        String input = scanner.nextLine();
        try {
            return LocalDateTime.parse(input, date);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date/time format. Please use yyyy-MM-dd HH:mm (e.g. 2025-04-01 14:30)");
            return null;
        }
    }
}



