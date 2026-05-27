
package com.example.demo.config;

import com.example.demo.interceptor.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;


/**
 * WebConfig: Configures Spring MVC settings including:
 * - CORS mappings for cross-origin requests
 * - AuthInterceptor registration for session validation
 * - Cache-control headers for secured pages
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthInterceptor authInterceptor;

    /**
     * Register AuthInterceptor to validate session on all requests
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/register", "/login", "/logout-success", "/forgot-password", "/success", "/about", "/mission", "/contact")
                .excludePathPatterns("/css/**", "/js/**", "/images/**", "/static/**", "/resources/**")
                .excludePathPatterns("/**/*.css", "/**/*.js", "/**/*.jpg", "/**/*.jpeg", "/**/*.png", "/**/*.gif", "/**/*.svg", "/**/*.woff", "/**/*.woff2", "/**/*.ttf");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                if (source == null || source.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(source);
            }
        });
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }
}
