package com.nexabank.customerservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customerServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("NexaBank — Customer Service API")
                        .description("Manages customer registration, profiles and KYC verification")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("NexaBank Team")
                                .email("dev@nexabank.com"))
                        .license(new License()
                                .name("Apache 2.0")))
                .servers(List.of(
                        new Server().url("http://localhost:8081").description("Local"),
                        new Server().url("http://localhost:8080").description("Via API Gateway")
                ));
    }
}