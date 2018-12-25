package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.ResponseStatus;
import lucrez.ceva.model.User;
import lucrez.ceva.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class AdminController {
    private IUserService userService;

    @GetMapping(value = "/test")
    public ResponseEntity<?> test() {
        return ResponseStatus.create();
    }

    @GetMapping(value="/users")
    public List<User> listUser(){
        return userService.getAll();
    }

    @GetMapping(value="/users/{page}-{size}")
    public List<User> listUserRange(@PathVariable Integer page, @PathVariable Integer size) {
        return userService.getRange(page, size);
    }

    @GetMapping(value="/users/{name}")
    public List<User> listUserName(@PathVariable String name) {
        return userService.getAll(name);
    }

    @GetMapping(value="/users/{name}/{page}-{size}")
    public List<User> listUserNameRange(@PathVariable String name, @PathVariable Integer page, @PathVariable Integer size) {
        return userService.getRange(name, page, size);
    }

    @PostMapping(value = "/delete-user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseStatus.create();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> catchExceptions(Exception ex) {
        return ResponseStatus.create(ex);
    }
}
