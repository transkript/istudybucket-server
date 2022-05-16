package com.elroykanye.istudybucket.config.global;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableWebMvc
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    private static final String[] ALLOWED_ORIGINS = new String[] {
            "http://localhost:8080",
            "http://localhost:4200",
            "http://localhost:8000",
            "http://localhost:4200",
            "http://localhost:3000"
    };

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
                .allowedOrigins(ALLOWED_ORIGINS);
    }
}
