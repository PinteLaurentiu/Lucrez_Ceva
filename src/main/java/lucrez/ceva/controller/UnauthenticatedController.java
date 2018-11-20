package lucrez.ceva.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unauthenticated")
public class UnauthenticatedController {
    @GetMapping(value = "/test")
    public String test() {
        return "Hello World!";
    }
}
