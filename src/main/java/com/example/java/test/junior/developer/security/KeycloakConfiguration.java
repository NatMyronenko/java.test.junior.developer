package com.example.java.test.junior.developer.security;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@Slf4j
@ConfigurationProperties(prefix = "keycloak")
public class KeycloakConfiguration {

  private String authServerUrl;
  private String realm;
  private String clientId;
  private String clientSecret;
  private final String authorizationType = "client_credentials";
  private String tokenUri;

  @Bean
  public Keycloak keycloak() {
    return KeycloakBuilder.builder()
        .serverUrl(authServerUrl)
        .grantType(authorizationType)
        .realm(realm)
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }


  public String getTokenUri() {
    String tokenUri = "http://localhost:8085/auth/realms/app/protocol/openid-connect/token";
    log.info("Getting token URI from configuration: {}", tokenUri);
    return tokenUri;
  }

  public void setTokenUri(String tokenUri) {
    this.tokenUri = tokenUri;
  }
}
