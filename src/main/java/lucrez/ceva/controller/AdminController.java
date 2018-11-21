package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.model.UserLogin;
import lucrez.ceva.service.interfaces.IUserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class AdminController {
    private IUserLoginService userLoginService;

    @GetMapping(value = "/test")
    public String test() {
        return "Hello admin!";
    }

    @GetMapping(value="/user")
    public List<UserLogin> listUser(){
        return userLoginService.findAll();
    }
}
