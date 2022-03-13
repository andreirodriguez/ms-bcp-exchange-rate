package pe.bcp.exchangerate.api.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "pe.bcp.exchangerate")
public class MsexchangerateApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsexchangerateApplication.class, args);
	}

}
