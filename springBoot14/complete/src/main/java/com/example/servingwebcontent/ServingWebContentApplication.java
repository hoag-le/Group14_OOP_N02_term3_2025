package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ServingWebContentApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

}
