package lucrez.ceva.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Encoders {
    private PasswordEncoder encoderOauth;
    private PasswordEncoder encoder;

    @Bean
    public PasswordEncoder oauthClientPasswordEncoder() {
        if (encoderOauth == null)
            encoderOauth = new BCryptPasswordEncoder(4);
        return encoderOauth;
    }

    @Bean
    public PasswordEncoder userPasswordEncoder() {
        if (encoder == null)
            encoder = new BCryptPasswordEncoder(8);
        return encoder;
    }
}
