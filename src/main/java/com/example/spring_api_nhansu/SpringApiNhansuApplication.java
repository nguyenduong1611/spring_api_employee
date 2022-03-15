package com.example.spring_api_nhansu;

import com.example.spring_api_nhansu.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringApiNhansuApplication {
    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(SpringApiNhansuApplication.class, args);
    }


//    @EventListener(ApplicationReadyEvent.class)
//    public void sendEmail(){
//        emailSenderService.sendEmail("nguyentramahoang@gmail.com", "this is Subject", "This is body");
//    }
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
}
