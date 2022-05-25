package task1.isdaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task1.isdaha.entity.Role;
import task1.isdaha.enums.RoleEnum;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role , Long> {
    Optional<Role> findByRole(RoleEnum user);
}
