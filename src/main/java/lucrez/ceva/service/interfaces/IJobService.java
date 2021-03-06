package lucrez.ceva.service.interfaces;

import lucrez.ceva.model.*;
import lucrez.ceva.model.enums.JobType;

import java.util.Date;
import java.util.List;

public interface IJobService {
    Job save(Job job);
    List<Job> getAll(JobFilter jobFilter);
    List<Job> getRange(JobPageableFilter jobFilter);
    List<Job> getAll(User current, JobFilter jobFilter);
    List<Job> getRange(User current, JobPageableFilter jobFilter);
    void delete(long id);
    Job getById(Long id);
    void approval(Long id, Boolean approved);
    Long countByType(JobType jobType);
    Long countByDate(Date date);
    Long size();
    List<JobAction> getHistory(User user);
}
