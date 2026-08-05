package com.nexabank.accountservice.config;

import com.nexabank.accountservice.client.CustomerClient;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.service.registry.ImportHttpServices;

@Configuration(proxyBeanMethods = false)
@ImportHttpServices(group = "customer", types = {CustomerClient.class})
public class CustomerClientConfig {
    @Bean
    RestClientHttpServiceGroupConfigurer customerGroupConfigurer(LoadBalancerClient loadBalancerClient, ObservationRegistry observationRegistry) {
        return groups -> groups.filterByName("customer")
                .forEachClient((group, builder) ->
                        builder.baseUrl("http://customer-service")
                                .requestInterceptor(new LoadBalancerInterceptor(loadBalancerClient))
                                .observationRegistry(observationRegistry));
    }
}
