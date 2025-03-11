package com.learn.newsfeed;

import io.micronaut.runtime.Micronaut;


public class Application {

    public static void main(String[] args) {
//        ParserService parserService = new ParserService();
//        ParserService.rrsNewsParser();
        Micronaut.run(Application.class, args);
    }
}