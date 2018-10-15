package com.inz.about;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class AboutApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AboutApplication.class, args);
    }
}
