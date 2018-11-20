package lucrez.ceva.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@FrameworkEndpoint
public class LogoutController {

    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;

    @PostMapping(value = "authenticated/logout")
    @ResponseBody
    public ResponseEntity<String> revokeToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.contains("Bearer")){
            String tokenId = authorization.substring("Bearer".length()+1);
            tokenServices.revokeToken(tokenId);
        }
        return new ResponseEntity<>("You are successfully logout", HttpStatus.OK);
    }
}