import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationSystem {
    private List <Workspace> workspaces;
    private List <Reservation> reservations;
    private User currentUser;
    private DateTimeFormatter date;


    public ReservationSystem(){
        workspaces = new ArrayList<>();
        reservations = new ArrayList<>();
        scanner = new Scanner(System.in);
        date = DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm");

        workspaces.add(new Workspace(1, "Open space", 10.0, true));
        workspaces.add(new Workspace(2, "Private room", 25.0, true));
        workspaces.add(new Workspace(3, "OMeeting room", 45.0, true));
    }
}
