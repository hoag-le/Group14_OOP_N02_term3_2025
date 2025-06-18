package com.example.complete;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Import class LibraryNotifier
import com.example.complete.LibraryNotifier;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) {
        // Chạy Spring Boot
        SpringApplication.run(ServingWebContentApplication.class, args);

        // Gọi chức năng thông báo sách sắp đến hạn
        LibraryNotifier.notifyUsers();
    }
}
