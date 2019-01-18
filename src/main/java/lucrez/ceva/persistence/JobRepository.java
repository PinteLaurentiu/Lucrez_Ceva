package lucrez.ceva.persistence;

import lucrez.ceva.model.Job;
import lucrez.ceva.model.User;
import lucrez.ceva.model.enums.JobType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long>, JobRepositoryCustom {
    Long countByJobType(JobType jobType);
    Long countByDate(Date date);
    List<Job> getByUser(User user);
}
