package pe.bcp.exchangerate.api.configuration;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	private static final String SECURED_PATTERN = "/**";
	
	@Autowired
	private Environment environment;
	
	@Value("${identity-provider.url-introspect}")
    private String url_introspect;
	
	@Value("${identity-provider.resource-id}")
    private String resource_id;
	
	@Value("${identity-provider.resource-secret}")
    private String secret;
	
	@Autowired
	private CustomAccessTokenConverter customAccessTokenConverter;
	
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws 
        Exception {
         resources.tokenServices(tokenService()).resourceId(resource_id).stateless(true);
    }

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		String encodedString = Base64.getEncoder().encodeToString((resource_id + ":" + secret).getBytes());
		converter.setSigningKey(encodedString);
		converter.setAccessTokenConverter(customAccessTokenConverter);
		return converter;
	}
	
	@Bean
    public RemoteTokenServices tokenService() {
        RemoteTokenServices tokenService = new RemoteTokenServices();
        tokenService.setCheckTokenEndpointUrl(url_introspect);
        tokenService.setClientId(resource_id); 
        tokenService.setClientSecret(secret);
        
        tokenService.setAccessTokenConverter(accessTokenConverter());
        return tokenService;
    }
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

		if (useSecurity()) {
			http.requestMatchers().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers("/actuator/**", "/v2/api-docs/**","/swagger-ui.html","/swagger-resources/**","/webjars/**").permitAll()
					.antMatchers(SECURED_PATTERN).authenticated();
		}else {
			http.requestMatchers().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers("/**/").permitAll()
			.antMatchers(SECURED_PATTERN).authenticated();
		}
	}

	private Boolean useSecurity() {
		return !environment.getProperty("disable-security", Boolean.class, Boolean.FALSE);
	}
}
