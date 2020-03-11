package com.zamoiski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MasteryJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasteryJavaApplication.class, args);
    }

}
