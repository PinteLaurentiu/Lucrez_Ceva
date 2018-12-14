package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.ResponseStatus;
import lucrez.ceva.model.UserLogin;
import lucrez.ceva.service.interfaces.IUserLoginService;
import lucrez.ceva.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class AdminController {
    private IUserLoginService userLoginService;
    private IUserService userService;

    @GetMapping(value = "/test")
    public ResponseEntity<?> test() {
        return ResponseStatus.create();
    }

    @GetMapping(value="/users")
    public List<UserLogin> listUser(){
        return userLoginService.findAll();
    }

    @PostMapping(value = "/delete-user")
    public ResponseEntity<?> deleteUser(@RequestParam Long id) {
        userService.delete(id);
        return ResponseStatus.create();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> catchExceptions(Exception ex) {
        return ResponseStatus.create(ex);
    }
}
