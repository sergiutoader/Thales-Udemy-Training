package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

    private final static String[] fortunes = {"Fortune 0", "Fortune 1", "Fortune 2"};

    @Override
    public String getFortune() {
        return fortunes[(int)(Math.random() * fortunes.length)];
    }
}
