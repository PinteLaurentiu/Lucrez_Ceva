package lucrez.ceva.service;

import lombok.AllArgsConstructor;
import lucrez.ceva.model.Job;
import lucrez.ceva.model.UserLogin;
import lucrez.ceva.persistence.JobRepository;
import lucrez.ceva.service.interfaces.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class JobService implements IJobService {
    private JobRepository jobRepository;

    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void delete(long id) {
        jobRepository.delete(id);
    }

    @Override
    public Job getById(Long id) {
        return jobRepository.findOne(id);
    }
}
