package io.github.leonidius20.twitterclone.authservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
public class TwitterAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterAuthServiceApplication.class, args);
    }

}
