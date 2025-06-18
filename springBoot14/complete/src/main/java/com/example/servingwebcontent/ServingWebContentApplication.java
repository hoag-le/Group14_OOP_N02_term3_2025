package com.example.servingwebcontent;

import com.example.servingwebcontent.models.Library;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            Library library = new Library();
            library.notifyBooksDueSoon();
        };
    }
}
