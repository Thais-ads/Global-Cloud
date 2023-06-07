package br.com.wecare.we_care_project;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableWebMvc
@Configuration
public class WeCareProjectApplication implements WebMvcConfigurer {


	public static void main(String[] args) {
		SpringApplication.run(WeCareProjectApplication.class, args);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://example.com", "http://localhost:8080")
				.allowedMethods("GET", "POST", "PUT", "DELETE")
				.allowedHeaders("*");

	}
}