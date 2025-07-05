package com.example.servingwebcontent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServingWebContentApplication {

    private static final Logger logger = LoggerFactory.getLogger(ServingWebContentApplication.class);

    public static void main(String[] args) {
        try {
            SpringApplication.run(ServingWebContentApplication.class, args);
        } catch (Exception e) {
            logger.error("Application failed to start", e);
        }
    }

}