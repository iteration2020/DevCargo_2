package com.example.devcargo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class CargoManagerApplication extends SpringBootServletInitializer {
    public CargoManagerApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(CargoManagerApplication.class, args);
    }
}
