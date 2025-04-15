import exceptions.DuplicateUserIDException;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface Repository<T, ID> {
    Optional<T> findById(ID id);

    List<Workspace> findAll();

    void save(T entity) throws DuplicateUserIDException;

    void delete(T entity);

    List<T> findByPredicate(Predicate<T> predicate);
}


