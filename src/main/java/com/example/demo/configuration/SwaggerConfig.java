package com.example.demo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .version("v1")
                        .title("라이징캠프 수업을 위한 데모 서비스 API")
                        .description("유저와 그룹 그리고 글과 댓글이 JPA 엔티티로 연결된 기본 CRUD")
                )
                .servers(Arrays.asList(
                        new Server().url("http://localhost:8080").description("로컬 서버"),
                        new Server().url("https://dev-api.example.com").description("개발 서버"),
                        new Server().url("https://api.example.com").description("운영 서버")
                ));
    }

    @Bean
    public GroupedOpenApi grouping() {
        String[] paths = {"/**"};
        return GroupedOpenApi.builder()
                .group("spec")
                .pathsToMatch(paths)
                .build();
    }

//  @Bean
//  public GroupedOpenApi userApi() {
//      String[] packagesToScan = {"com.example.controller.user"};
//      String[] paths = {"/api/v1/users/**"};
//      return GroupedOpenApi.builder()
//              .group("유저 관리 API")
////            .packagesToScan(packagesToScan)
//              .pathsToMatch(paths)
//              .build();
//  }

//  @Bean
//  public GroupedOpenApi postApi() {
//      String[] packagesToScan = {"com.example.controller.post"};
//      String[] paths = {"/api/v1/posts/**"};
//      return GroupedOpenApi.builder()
//              .group("글 관리 API")
////            .packagesToScan(packagesToScan)
//              .pathsToMatch(paths)
//              .build();
//  }
}
