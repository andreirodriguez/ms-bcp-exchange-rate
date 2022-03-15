package pe.bcp.exchangerate.api.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "pe.bcp.exchangerate")
@EnableSwagger2
@EnableResourceServer
public class MsexchangerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsexchangerateApplication.class, args);
	}

}
