package task1.isdaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task1.isdaha.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User , Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String username);
}
