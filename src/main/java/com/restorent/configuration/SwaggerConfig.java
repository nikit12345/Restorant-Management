package com.restorent.configuration;
import java.util.Arrays;
    import java.util.List;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import java.util.Collections;

    import springfox.documentation.builders.PathSelectors;
    import springfox.documentation.builders.RequestHandlerSelectors;
    import springfox.documentation.service.ApiInfo;
    import springfox.documentation.service.ApiKey;
    import springfox.documentation.service.AuthorizationScope;
    import springfox.documentation.service.Contact;
    import springfox.documentation.service.SecurityReference;
    import springfox.documentation.spi.DocumentationType;
    import springfox.documentation.spi.service.contexts.SecurityContext;
    import springfox.documentation.spring.web.plugins.Docket;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import io.swagger.v3.oas.models.OpenAPI;
    import io.swagger.v3.oas.models.info.Info;
    import io.swagger.v3.oas.models.security.SecurityRequirement;
    import io.swagger.v3.oas.models.security.SecurityScheme;

    import java.util.Collections;

    @Configuration
    public class SwaggerConfig {

        public static final String AUTHORIZATION_HEADER = "Authorization";
        public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";

        private ApiKey apiKey() {
            return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
        }

        private ApiInfo apiInfo() {
            return new ApiInfo(
                    "Spring Boot REST APIs",
                    "Spring Boot REST API Documentation",
                    "1.0",
                    "Terms of service",
                    new Contact("Your Name", "www.example.com", "your-email@example.com"),
                    "License of API",
                    "API license URL",
                    Collections.emptyList()
            );
        }

        
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                            .title("Your API Title")
                            .version("1.0")
                            .description("API documentation"))
                    .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                    .components(new io.swagger.v3.oas.models.Components()
                            .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                    .name("bearerAuth")
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")));
        }

        private SecurityContext securityContext() {
            return SecurityContext.builder()
                    .securityReferences(defaultAuth())
                    .forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                    .build();
        }

        private List<SecurityReference> defaultAuth() {
            AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
            AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
            authorizationScopes[0] = authorizationScope;
            return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
        }
    }
