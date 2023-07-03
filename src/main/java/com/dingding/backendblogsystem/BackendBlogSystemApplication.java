package com.dingding.backendblogsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.dingding.backendblogsystem.filter")
public class BackendBlogSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendBlogSystemApplication.class, args);
    }

}
