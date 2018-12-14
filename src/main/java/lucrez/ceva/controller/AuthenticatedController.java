package lucrez.ceva.controller;

import lombok.AllArgsConstructor;
import lucrez.ceva.dto.ResponseStatus;
import lucrez.ceva.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor=@__({@Autowired}))
public class AuthenticatedController {
    @GetMapping(value = "/authenticated/test")
    public ResponseEntity<?> test() {
        return ResponseStatus.create();
    }

    @GetMapping(value = {"/authenticated/principal"})
    public UserDetails userPrincipal(Authentication authentication){
        return (UserDetails) authentication.getPrincipal();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> catchExceptions(Exception ex) {
        return ResponseStatus.create(ex);
    }
}
