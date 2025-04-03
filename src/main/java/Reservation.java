import java.time.LocalDateTime;

public class Reservation {
    private int id;
    private String customerName;
    private Workspace workspace;
    private LocalDateTime startTime;
    private LocalDateTime endTime;


    public Reservation(int id, String customerName, Workspace workspace,
                       LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.customerName = customerName;
        this.workspace = workspace;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Workspace getWorkspace() {
        return workspace;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
