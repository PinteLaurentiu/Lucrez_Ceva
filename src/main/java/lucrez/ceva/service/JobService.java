package lucrez.ceva.service;

import lombok.AllArgsConstructor;
import lucrez.ceva.model.*;
import lucrez.ceva.model.enums.JobAcceptanceType;
import lucrez.ceva.model.enums.JobType;
import lucrez.ceva.persistence.JobRepository;
import lucrez.ceva.service.interfaces.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class JobService implements IJobService {
    private JobRepository jobRepository;

    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> getAll(JobFilter jobFilter) {
        return jobRepository.getAllFiltered(jobFilter);
    }

    @Override
    public List<Job> getRange(JobPageableFilter jobFilter) {
        return jobRepository.getAllFiltered(jobFilter);
    }

    @Override
    public List<Job> getAll(User current, JobFilter jobFilter) {
        List<String> tags = current.getTags().stream().map(UserTag::getTag).collect(Collectors.toList());
        return jobRepository.getByTag(tags, jobFilter);
    }

    @Override
    public List<Job> getRange(User current, JobPageableFilter jobFilter) {
        List<String> tags = current.getTags().stream().map(UserTag::getTag).collect(Collectors.toList());
        return jobRepository.getByTag(tags, jobFilter);
    }

    @Override
    public void delete(long id) {
        jobRepository.delete(id);
    }

    @Override
    public Job getById(Long id) {
        return jobRepository.findOne(id);
    }

    @Override
    public void approval(Long id, Boolean approved) {
        Job job = jobRepository.findOne(id);
        if (job == null)
            throw new NullPointerException();
        job.setAcceptanceType(approved ? JobAcceptanceType.Accepted : JobAcceptanceType.Declined);
        jobRepository.save(job);
    }

    @Override
    public Long countByType(JobType jobType) {
        return jobRepository.countByJobType(jobType);
    }

    @Override
    public Long countByDate(Date date) {
        return jobRepository.countByDate(date);
    }

    @Override
    public Long size() {
        return jobRepository.count();
    }
}
