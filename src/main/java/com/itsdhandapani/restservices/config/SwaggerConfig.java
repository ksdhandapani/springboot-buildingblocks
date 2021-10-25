package com.itsdhandapani.restservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) // To populate the JSR-303 Bean Validation
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getApiInfo())
				.select()
				//.apis(RequestHandlerSelectors.any())
				.apis(RequestHandlerSelectors.basePackage("com.itsdhandapani.restservices"))
				//.paths(PathSelectors.any())
				.paths(PathSelectors.ant("/users/**")) // Controllers have /users/** as a path will be shown in Swagger
				.build();
	}

	// Swagger Metadata - http://localhost:8080/v2/api-docs
	// Swagger UI URL - http://localhost:8080/swagger-ui.html
	// To verify that our Swagger API Documentation is Open API complaint or not  - https://editor.swagger.io/

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("DTechIdeas - User Management Service")
				.description("This page gives information about the APIs of User Management Service")
				.version("1.0")
				.contact(new Contact("DTechIdeas", "https://dtechideas.blogspot.com/", "ksdhandapani96@gmail.com"))
				.build();
	}

}
