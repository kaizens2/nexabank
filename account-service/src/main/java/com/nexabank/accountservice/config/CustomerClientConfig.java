package com.nexabank.accountservice.config;

import com.nexabank.accountservice.client.CustomerClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@ImportHttpServices(group = "customer", types = {CustomerClient.class})
public class CustomerClientConfig {
}
