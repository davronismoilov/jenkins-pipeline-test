package task1.isdaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.isdaha.entity.Seats;

public interface SeatsRepository  extends JpaRepository<Seats , Long> {
}
