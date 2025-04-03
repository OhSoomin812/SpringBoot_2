package org.soomin.sb2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@Configuration
//@EnableWebMvc
public class CustomServletConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 모든 엔드포인트에 대해 CORS 적용
                .allowedOriginPatterns("*") // 허용할 도메인 (필요에 따라 변경)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 허용할 HTTP 메서드
                .allowedHeaders("*") // 모든 헤더 허용
                .allowCredentials(true) // 쿠키 및 인증 정보 포함 허용
                .maxAge(3600); // preflight 요청 캐싱 시간 (초 단위)
    }
}
