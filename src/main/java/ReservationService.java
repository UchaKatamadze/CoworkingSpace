import exceptions.DuplicateUserIDException;

import java.util.List;
import java.util.Optional;

public class ReservationService implements CrudService<Reservation, Integer> {
    private final Repository<Reservation, Integer> repository;

    public ReservationService(Repository<Reservation, Integer> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Reservation> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Reservation> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Reservation entity) throws DuplicateUserIDException {
        repository.save(entity);
    }

    @Override
    public void delete(Reservation entity) {
        repository.delete(entity);
    }

    // Additional specific methods for reservation business logic
    public List<Reservation> findByCustomer(String customerName) {
        return repository.findByPredicate(r -> r.getCustomerName().equals(customerName));
    }
}