import exceptions.DuplicateUserIDException;

import java.util.List;
import java.util.Optional;

public class WorkspaceService implements CrudService<Workspace, Integer> {
    private final Repository<Workspace, Integer> repository;

    public WorkspaceService(Repository<Workspace, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Workspace> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Workspace> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Workspace entity) throws DuplicateUserIDException {
        repository.save(entity);
    }

    @Override
    public void delete(Workspace entity) {
        repository.delete(entity);
    }

    // Additional specific methods
    public List<Workspace> findAvailableWorkspaces() {
        return repository.findByPredicate(Workspace::isAvailable);
    }
}