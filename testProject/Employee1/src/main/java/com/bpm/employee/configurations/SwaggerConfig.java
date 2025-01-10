package com.bpm.employee.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;


	@Configuration
	@EnableSwagger2
	public class SwaggerConfig {

		@Bean
		public Docket api() {
			return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.bpm.employee.controller.client"))
				.build().apiInfo(apiEndPointsInfo());
		}


		private ApiInfo apiEndPointsInfo() {

		Contact contact = new Contact("Alejandro", "https://github.com/SuberoPrueba", "blu@Gmail.com");

			return new ApiInfoBuilder()
				.title("Employee")
				.description("Description: Employee’s hamblet")
				.contact(contact)
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.version("1.0.0.0")
				.build();
	 }

}

