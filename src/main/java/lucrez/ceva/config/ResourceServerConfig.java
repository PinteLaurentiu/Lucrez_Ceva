package lucrez.ceva.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
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
		http.authorizeRequests()
				.antMatchers("/", "/webjars/**", "/unauthenticated/**").permitAll()
				.antMatchers("/authenticated/**","/oauth/token").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest()
						.authenticated().and().logout()
						.logoutSuccessUrl("/").permitAll().and().csrf()
						.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        // OAuth2AccessDeniedHandler ->
        //		If authorization fails and the caller has asked for a specific content type response,
		// 			this entry point can send one, along with a standard 403 status.
		// 		Add to the Spring Security configuration as an AccessDeniedHandler in the usual way.
	}

}