package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.ResponseError;
import lucrez.ceva.dto.ResponseStatus;
import lucrez.ceva.dto.UpdateUserDto;
import lucrez.ceva.dto.mappers.UpdateUserMapper;
import lucrez.ceva.model.User;
import lucrez.ceva.model.UserDetails;
import lucrez.ceva.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class AuthenticatedController {
    private IUserService userService;

    @GetMapping(value = "/authenticated/test")
    public ResponseEntity<?> test() {
        return ResponseStatus.create();
    }

    @GetMapping(value = {"/authenticated/principal"})
    public UserDetails userPrincipal(Authentication authentication){
        return (UserDetails) authentication.getPrincipal();
    }


}
