package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.JobDto;
import lucrez.ceva.dto.ResponseError;
import lucrez.ceva.dto.ResponseStatus;
import lucrez.ceva.dto.mappers.ApplicationMapper;
import lucrez.ceva.dto.mappers.JobMapper;
import lucrez.ceva.model.Application;
import lucrez.ceva.model.Job;
import lucrez.ceva.model.User;
import lucrez.ceva.service.interfaces.IApplicationService;
import lucrez.ceva.service.interfaces.IJobService;
import lucrez.ceva.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class JobController {
    private IJobService jobService;
    private IUserService userService;
    private IApplicationService applicationService;

    @GetMapping("/authenticated/getAll")
    public List<Job> getAll(){
        return jobService.findAll();
    }

    @PostMapping("/authenticated/add")
    public ResponseEntity<?> save(@RequestBody JobDto jobDto){
        User user = userService.getCurrent();
        Job job = JobMapper.dtoToModelJob(user, jobDto);
        jobService.save(job);
        return ResponseStatus.create();
    }

    @PostMapping("/authenticated/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody JobDto jobDto){
        try {
            Job job = jobService.getById(id);//TODO : throw an Exception maybe differently
            JobMapper.updateModelWithDto(job, jobDto);
            jobService.save(job);
            return ResponseStatus.create();
        }catch (Exception ex) {
            return ResponseError.create(ex.getMessage());
        }
    }

    @PostMapping("/authenticated/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try{
            jobService.delete(id);
            return ResponseStatus.create();
        } catch (Exception ex) {
            return ResponseError.create(ex.getMessage());
        }
    }

    @PostMapping("/authenticated/apply/{id}")
    public ResponseEntity<?> apply(@PathVariable Long id){
        Job job = jobService.getById(id);
        User user = userService.getCurrent();
        Application application = ApplicationMapper.linkUserJob(user, job);
        applicationService.save(application);
        return ResponseStatus.create();
    }

    @PostMapping("/authenticated/bookmark/{id}")
    public ResponseEntity<?> bookmark(@PathVariable Long id){
        Job job = jobService.getById(id);
        User user = userService.getCurrent();
        user.getBookmarks().add(job);
        userService.update(user);
        return ResponseStatus.create();
    }
}
