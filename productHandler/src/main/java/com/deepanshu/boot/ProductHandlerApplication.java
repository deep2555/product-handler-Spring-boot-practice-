package com.deepanshu.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
		info = @Info(
				title = "Product Service",
				description = "Product Service Rest Api Documentation",
				version = "V1",
				contact = @Contact(
						name = "Deepanshu",
						email = "deepanshu@gmail.com"
						)
				)
		)
@SpringBootApplication
public class ProductHandlerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductHandlerApplication.class, args);
		System.out.println("spring boot practice");
	}

}
