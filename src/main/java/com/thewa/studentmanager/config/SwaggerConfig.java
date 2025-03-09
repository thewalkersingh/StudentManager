package com.thewa.studentmanager.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
   @Bean
   public OpenAPI studentManagementAPI() {
	  return new OpenAPI()
			  .info(new Info()
							.title("Student Management API")
							.description("API for managing students and courses")
							.version("1.0"));
   }
}