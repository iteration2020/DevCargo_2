package com.example.devcargo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public MvcConfig() {
    }

    public void addViewControllers(ViewControllerRegistry registry) {
    }
}
