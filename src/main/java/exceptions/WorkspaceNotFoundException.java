package exceptions;

public class WorkspaceNotFoundException extends Exception {
    public WorkspaceNotFoundException(int id) {
        super("Workspace not found with ID: " + id);
    }
}

