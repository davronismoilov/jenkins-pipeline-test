package task1.isdaha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task1.isdaha.entity.Program;

public interface ProgramRepository  extends JpaRepository<Program,Long> {
}
