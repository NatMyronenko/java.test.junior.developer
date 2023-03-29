package com.example.java.test.junior.developer.configuration;

import javax.ws.rs.core.HttpHeaders;
import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

  @Value("${keycloak.tokenUrl}")
  private String tokenUrl;

  @Bean
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder()
        .baseUrl(tokenUrl)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
  }
}
