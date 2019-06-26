package io.vishnu.gw.config;

import io.vishnu.gw.filter.ErrorFilter;
import io.vishnu.gw.filter.PostFilter;
import io.vishnu.gw.filter.PreFilter;
import io.vishnu.gw.filter.RouteFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring-boot-microservice : io.vishnu.gw.config
 *
 * @author vishnu.g
 */
@Configuration
public class Configurations {
    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }
    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }
    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }
    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")                  // For all path by default
                        .allowedOrigins("*")                           // From anywhere by default
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE");
            }
        };
    }
}
