package com.example.springboot.uploadfileprogress;

import com.example.springboot.uploadfileprogress.config.UploadMultipartResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;

@SpringBootApplication
public class UploadfileprogressApplication {

    public static void main(String[] args) {
        SpringApplication.run(UploadfileprogressApplication.class, args);
    }
}
