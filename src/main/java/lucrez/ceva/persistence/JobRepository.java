package lucrez.ceva.persistence;

import lucrez.ceva.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
