package lucrez.ceva.controller;

//import lucrez.ceva.model.UserDetailsWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticated")
public class AuthenticatedController {
    @GetMapping(value = "/test")
    public String test() {
        return "Hello authenticated user!";
    }

//    @GetMapping(value = {"/principal"})
//    public UserDetailsWrapper userPrincipal(Authentication authentication){
//        return (UserDetailsWrapper) authentication.getPrincipal();
//    }
}
