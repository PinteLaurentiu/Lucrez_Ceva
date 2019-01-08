package lucrez.ceva.persistence;

import lucrez.ceva.model.Job;
import lucrez.ceva.model.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryCustom {
    Long countByJobType(JobType jobType);
    Long countByDate(Date date);
}
