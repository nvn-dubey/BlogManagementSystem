package com.blog.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.blog.blog")
public class BlogManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogManagementSystemApplication.class, args);
    }
}
