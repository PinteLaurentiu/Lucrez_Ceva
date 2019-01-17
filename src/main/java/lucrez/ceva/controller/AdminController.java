package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.JobDateStatisticDto;
import lucrez.ceva.dto.JobTypeStatisticDto;
import lucrez.ceva.dto.ResponseStatus;
import lucrez.ceva.dto.mappers.UserAdminMapper;
import lucrez.ceva.dto.mappers.UserMapper;
import lucrez.ceva.model.User;
import lucrez.ceva.model.enums.JobType;
import lucrez.ceva.service.interfaces.IJobService;
import lucrez.ceva.service.interfaces.IUserService;
import lucrez.ceva.service.staticService.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class AdminController {
    private IUserService userService;
    private IJobService jobService;
    private UserMapper mapper;

    @GetMapping(value = "/test")
    public ResponseEntity<?> test() {
        return ResponseStatus.create();
    }

    @GetMapping(value="/users")
    public ResponseEntity<?> listUser(){
        return new ResponseEntity<>(UserAdminMapper.toDto(userService.getAll()), HttpStatus.OK);
    }

    @GetMapping(value="/users/{page}-{size}")
    public ResponseEntity<?> listUserRange(@PathVariable int page, @PathVariable int size) {
        return new ResponseEntity<>(UserAdminMapper.toDto(userService.getRange(page, size)), HttpStatus.OK);
    }

    @GetMapping(value="/users_search/{name}")
    public ResponseEntity<?> listUserName(@PathVariable String name) {
        return new ResponseEntity<>(UserAdminMapper.toDto(userService.getAll(name)), HttpStatus.OK);
    }

    @GetMapping(value="/users_search/{name}/{page}-{size}")
    public ResponseEntity<?> listUserNameRange(@PathVariable String name, @PathVariable Integer page, @PathVariable Integer size) {
        return new ResponseEntity<>(UserAdminMapper.toDto(userService.getRange(name, page, size)), HttpStatus.OK);
    }

    @GetMapping(value = "/users_count")
    public ResponseEntity<?> usersCount() {
        return new ResponseEntity<>(userService.size(), HttpStatus.OK);
    }

    @PostMapping(value = "/delete_user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseStatus.create();
    }

    @PostMapping(value = "/change_password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @RequestBody String password) {
        userService.updatePassword(id, mapper.encodePassword(password), password);
        return ResponseStatus.create();
    }

    @PostMapping(value = "/job_approval/{id}/{approved}")
    public ResponseEntity<?> approval(@PathVariable Long id, @PathVariable Boolean approved) {
        jobService.approval(id, approved);
        return ResponseStatus.create();
    }

    @GetMapping(value = "/job_type_statistics")
    public ResponseEntity<?> jobTypeStatistics() {
        return new ResponseEntity<>(Arrays.stream(JobType.values())
                .map(x->new JobTypeStatisticDto(x, jobService.countByType(x)))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = "/job_date_statistics/{days}")
    public ResponseEntity<?> jobDateStatistics(@PathVariable Integer days) {
        Date date = new Date();
        List<JobDateStatisticDto> statistics = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            statistics.add(new JobDateStatisticDto(date, jobService.countByDate(date)));
            date = DateTimeService.addDays(date, -1);
        }
        return new ResponseEntity<>(statistics, HttpStatus.OK);
    }

    @GetMapping(value = "/job_count")
    public ResponseEntity<?> jobCount() {
        return new ResponseEntity<>(jobService.size(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> catchExceptions(Exception ex) {
        return ResponseStatus.create(ex);
    }
}
