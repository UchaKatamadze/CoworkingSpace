import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReservationRepository implements Repository<Reservation, Integer> {
    private final List<Reservation> reservations;

    public ReservationRepository(List<Reservation> initialReservations) {
        this.reservations = new ArrayList<>(initialReservations);
    }

    @Override
    public Optional<Reservation> findById(Integer id) {
        return reservations.stream()
                .filter(r -> r.getId() == id)
                .findFirst();
    }

    @Override
    public List<Workspace> findAll() {
        return new ArrayList<>();
    }

    @Override
    public void save(Reservation entity) {
        // Store ID value to use in lambda
        final int entityId = entity.getId();

        // If it's a new reservation (no existing ID)
        if (reservations.stream().noneMatch(r -> r.getId() == entityId)) {
            // Generate new ID if needed
            if (entity.getId() == 0) {
                int newId = reservations.isEmpty() ? 1 :
                        reservations.stream().mapToInt(Reservation::getId).max().getAsInt() + 1;
                // Create new reservation with the generated ID
                entity = new Reservation(
                        newId,
                        entity.getCustomerName(),
                        entity.getWorkspace(),
                        entity.getStartTime(),
                        entity.getEndTime()
                );
            }
            reservations.add(entity);
        } else {
            // Update existing reservation
            for (int i = 0; i < reservations.size(); i++) {
                if (reservations.get(i).getId() == entity.getId()) {
                    reservations.set(i, entity);
                    break;
                }
            }
        }
    }

    @Override
    public void delete(Reservation entity) {
        reservations.removeIf(r -> r.getId() == entity.getId());
    }

    @Override
    public List<Reservation> findByPredicate(Predicate<Reservation> predicate) {
        return reservations.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}