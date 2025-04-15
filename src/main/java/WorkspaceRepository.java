import exceptions.DuplicateUserIDException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorkspaceRepository implements Repository<Workspace, Integer> {
    private final List<Workspace> workspaces;

    public WorkspaceRepository(List<Workspace> initialWorkspaces) {
        this.workspaces = new ArrayList<>(initialWorkspaces);
        // Add default workspaces if the list is empty
        if (workspaces.isEmpty()) {
            try {
                save(new Workspace(1, "Open space", 10.0, true));
                save(new Workspace(2, "Private room", 25.0, true));
                save(new Workspace(3, "Meeting room", 45.0, true));
            } catch (DuplicateUserIDException e) {
                System.out.println("Error loading workspaces: " + e.getMessage());
            }
        }
    }

    @Override
    public Optional<Workspace> findById(Integer id) {
        return workspaces.stream()
                .filter(w -> w.getId() == id)
                .findFirst();
    }

    @Override
    public List<Workspace> findAll() {
        return new ArrayList<>(workspaces);
    }

    @Override
    public void save(Workspace entity) throws DuplicateUserIDException {
        // Check for duplicate workspace ID
        if (workspaces.stream().anyMatch(w -> w.getId() == entity.getId())) {
            throw new DuplicateUserIDException("Workspace with ID " + entity.getId() + " already exists");
        }
        workspaces.add(entity);
    }

    @Override
    public void delete(Workspace entity) {
        workspaces.removeIf(w -> w.getId() == entity.getId());
    }

    @Override
    public List<Workspace> findByPredicate(Predicate<Workspace> predicate) {
        return workspaces.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}