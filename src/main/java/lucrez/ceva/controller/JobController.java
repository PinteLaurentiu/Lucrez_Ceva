package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.JobActionDto;
import lucrez.ceva.dto.JobAddDto;
import lucrez.ceva.dto.ResponseStatus;
import lucrez.ceva.dto.mappers.ApplicationMapper;
import lucrez.ceva.dto.mappers.JobActionMapper;
import lucrez.ceva.dto.mappers.JobMapper;
import lucrez.ceva.model.*;
import lucrez.ceva.service.interfaces.IApplicationService;
import lucrez.ceva.service.interfaces.IJobService;
import lucrez.ceva.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Problema 1 BOULE
@RestController
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class JobController {
    private IJobService jobService;
    private IUserService userService;
    private IApplicationService applicationService;

    @PostMapping("/unauthenticated/getAll")
    public ResponseEntity<?> getAll(@RequestBody JobFilter jobFilter) {
        User current = userService.getCurrent();
        return new ResponseEntity<>(JobMapper.mapJobSimpleDto(jobService.getAll(jobFilter), current), HttpStatus.OK);
    }

    @PostMapping("/unauthenticated/getAll/{page}-{size}")
    public ResponseEntity<?> getAll(@RequestBody JobPageableFilter jobFilter,
                                    @PathVariable int page,
                                    @PathVariable int size) {
        User current = userService.getCurrent();
        jobFilter.setPage(page);
        jobFilter.setPageSize(size);
        return new ResponseEntity<>(JobMapper.mapJobSimpleDto(jobService.getRange(jobFilter), current), HttpStatus.OK);
    }

    @PostMapping("/unauthenticated/getAllDetailed/{page}-{size}")
    public ResponseEntity<?> getAllDetailed(@RequestBody JobPageableFilter jobFilter,
                                    @PathVariable int page,
                                    @PathVariable int size) {
        User current = userService.getCurrent();
        jobFilter.setPage(page);
        jobFilter.setPageSize(size);
        return new ResponseEntity<>(JobMapper.createJobDetailedDto(jobService.getRange(jobFilter), current), HttpStatus.OK);
    }

    @PostMapping("/authenticated/getAll")
    public ResponseEntity<?> getRecommended(@RequestBody JobFilter jobFilter) {
        User current = userService.getCurrent();
        return new ResponseEntity<>(JobMapper.mapJobSimpleDto(jobService.getAll(current, jobFilter), current), HttpStatus.OK);
    }

    @PostMapping("/authenticated/getAll/{page}-{size}")
    public ResponseEntity<?> getRecommended(@RequestBody JobPageableFilter jobFilter,
                                            @PathVariable int page,
                                            @PathVariable int size) {
        User current = userService.getCurrent();
        jobFilter.setPage(page);
        jobFilter.setPageSize(size);
        return new ResponseEntity<>(JobMapper.mapJobSimpleDto(jobService.getAll(current, jobFilter), current), HttpStatus.OK);
    }

    @PostMapping("/authenticated/add")
    public ResponseEntity<?> save(@RequestBody JobAddDto jobAddDto){
        User user = userService.getCurrent();
        Job job = JobMapper.dtoToModelJob(user, jobAddDto);
        jobService.save(job);
        return ResponseStatus.create();
    }

    @PostMapping("/authenticated/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody JobAddDto jobAddDto){
        Job job = jobService.getById(id);
        JobMapper.updateModelWithDto(job, jobAddDto);
        jobService.save(job);
        return ResponseStatus.create();
    }

    @PostMapping("/authenticated/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        jobService.delete(id);
        return ResponseStatus.create();
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
        if (job == null)
            throw new NullPointerException();
        User user = userService.getCurrent();
        user.getBookmarks().add(job);
        userService.update(user);
        return ResponseStatus.create();
    }

    @PostMapping("/authenticated/remove_bookmark/{id}")
    public ResponseEntity<?> remove_bookmark(@PathVariable Long id){
        User user = userService.getCurrent();
        Job job = user.getBookmarks().stream().filter(x-> x.getId().equals(id)).findAny().orElse(null);
        if (job == null)
            throw new NullPointerException();
        user.getBookmarks().remove(job);
        userService.update(user);
        return ResponseStatus.create();
    }

    @GetMapping("/authenticated/getBookmarks")
    public ResponseEntity<?> getBookmarks() {
        User user = userService.getCurrent();
        return new ResponseEntity<>(JobMapper.mapJobSimpleDto(user.getBookmarks(), user), HttpStatus.OK);
    }

    @GetMapping("/unauthenticated/getJob/{id}")
    public ResponseEntity<?> getJob(@PathVariable Long id) {
        User user = userService.getCurrent();
        Job job = jobService.getById(id);
        if (job == null)
            throw new NullPointerException();
        return new ResponseEntity<>(JobMapper.createJobDetailedDto(job, user), HttpStatus.OK);
    }

    @GetMapping("/authenticated/history")
    public ResponseEntity<?> getHistory() {
        User user = userService.getCurrent();
        List<JobAction> actions = jobService.getHistory(user);
        return new ResponseEntity<>(JobActionMapper.toDto(actions,user), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> catchExceptions(Exception ex) {
        return ResponseStatus.create(ex);
    }
}
