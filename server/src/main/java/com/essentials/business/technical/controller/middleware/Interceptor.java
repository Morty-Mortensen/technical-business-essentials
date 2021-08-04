package com.essentials.business.technical.controller.middleware;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class Interceptor extends WebMvcConfigurerAdapter {

    /**
     * Adding a new interceptor.
     *
     * <p>
     * 1. Make sure that the error handling url (/error) is added.
     * 2. Forward calls using ExceptionHandler.forwardException().
     * <p>
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CacheMiddleware()).excludePathPatterns("/user/validate", "/user", "/error", "/user/logout");
        super.addInterceptors(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("Access-Control-Allow-Origin",
                        "Access-Control-Allow-Credentials",
                        "Access-Control-Allow-Methods",
                        "Access-Control-Allow-Headers",
                        "Origin",
                        "X-Requested-With",
                        "Content-Type",
                        "Accept",
                        "Authorization",
                        "Cache-Control",
                        "Pragma")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedOrigins("http://localhost:4200")
                .allowCredentials(true);
    }
}
