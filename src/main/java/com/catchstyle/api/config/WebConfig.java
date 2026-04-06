package com.catchstyle.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 서버의 모든 API 엔드포인트에 대해 통신을 허용함
                .allowedOrigins("http://localhost:3000","https://catchstyle-mvp.vercel.app") // 접근을 허용할 프론트엔드 주소
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 HTTP 헤더 허용
                .allowCredentials(true) // 쿠키나 인증 정보(토큰)를 포함한 요청을 허용함
                .maxAge(3600); // 사전 요청(Preflight) 결과를 1시간(3600초) 동안 캐시함
    }
}