package org.newsfeed;

import io.micronaut.runtime.Micronaut;
import org.newsfeed.service.ParserService;

import javax.swing.text.html.parser.Parser;

public class Application {

    public static void main(String[] args) {
        ParserService parserService = new ParserService();
        ParserService.rrsNewsParser();
        Micronaut.run(Application.class, args);
    }
}