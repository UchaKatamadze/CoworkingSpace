import exceptions.DuplicateUserIDException;

import java.util.List;
import java.util.Optional;

public interface CrudService<T, ID> {
    Optional<T> findById(ID id);

    List<T> findAll();

    void save(T entity) throws DuplicateUserIDException;

    void delete(T entity);
}
