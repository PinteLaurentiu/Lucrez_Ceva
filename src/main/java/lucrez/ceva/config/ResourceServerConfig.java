package lucrez.ceva.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${security.oauth2.resource-ids}")
	private String resourceId;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(resourceId).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
            .authorizeRequests()
                .antMatchers( "/", "/webjars/**", "/unauthenticated/**", "/oauth/token").permitAll()
				.antMatchers("/authenticated/**").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
			.and().logout().permitAll()
            .and().csrf().disable();
	}

}