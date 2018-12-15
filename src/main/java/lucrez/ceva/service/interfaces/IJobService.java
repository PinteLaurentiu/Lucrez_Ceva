package lucrez.ceva.service.interfaces;

import lucrez.ceva.model.Job;
import java.util.List;

public interface IJobService {
    Job save(Job job);
    List<Job> findAll();
    void delete(long id);
    Job getById(Long id);
}
