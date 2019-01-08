package lucrez.ceva.persistence;

import lucrez.ceva.model.Job;
import lucrez.ceva.model.JobFilter;
import lucrez.ceva.model.JobPageableFilter;

import java.util.List;

public interface JobRepositoryCustom {
    List<Job> getAllFiltered(JobFilter filter);
    List<Job> getAllFiltered(JobPageableFilter filter);
    List<Job> getByTag(List<String> tags, JobFilter filter);
    List<Job> getByTag(List<String> tags, JobPageableFilter filter);
}
