package lucrez.ceva.controller;

import lucrez.ceva.dto.ResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LogoutController {

    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;

    @PostMapping(value = "authenticated/logout")
    @ResponseBody
    public ResponseEntity<?> revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")){
            String tokenId = authorization.substring("Bearer".length()+1);
            tokenServices.revokeToken(tokenId);
        }
        return ResponseStatus.create();
    }
}