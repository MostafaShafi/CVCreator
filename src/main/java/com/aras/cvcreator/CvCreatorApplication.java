package com.aras.cvcreator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CvCreatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(CvCreatorApplication.class, args);
    }

    @Bean
    public String profileImageDir() {
        return "D:/Proje/Git/CVCreator/src/main/resources/static/images/profile";
    }

    @Bean
    public String backgroundImageDir() {
        return "D:/Proje/Git/CVCreator/src/main/resources/static/images/background";
    }
}
