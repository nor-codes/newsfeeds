package com.learn.newsfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NewsFeedParserApplication {
    public static void main(String[] args) {
        SpringApplication.run(NewsFeedParserApplication.class, args);
    }
}
