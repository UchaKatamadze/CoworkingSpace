import java.util.ArrayList;
import java.util.List;

public class ReservationSystem {
    private final Repository<Workspace, Integer> workspaceRepository;
    private final Repository<Reservation, Integer> reservationRepository;
    private final CrudService<Workspace, Integer> workspaceService;
    private final CrudService<Reservation, Integer> reservationService;
    private User currentUser;

    public ReservationSystem() {
        // Initialize repositories with initial data
        List<Workspace> initialWorkspaces = new ArrayList<>();
        // Add some default workspaces if needed
        this.workspaceRepository = new WorkspaceRepository(initialWorkspaces);
        this.reservationRepository = new ReservationRepository(new ArrayList<>());

        // Initialize services with repositories
        this.workspaceService = new WorkspaceService(workspaceRepository);
        this.reservationService = new ReservationService(reservationRepository);
    }

    private void makeReservation(Customer customer) {
        // Use the generic services
        List<Workspace> availableWorkspaces = ((WorkspaceService) workspaceService).findAvailableWorkspaces();
        // Rest of implementation
    }

    private void viewMyReservations(Customer customer) {
        // Use the generic repositories with predicates
        List<Reservation> customerReservations = reservationRepository.findByPredicate(
                r -> r.getCustomerName().equals(customer.getName())
        );
        // Display reservations
    }

    public void start() {
    }
}



