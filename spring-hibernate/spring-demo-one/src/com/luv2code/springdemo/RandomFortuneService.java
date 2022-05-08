package com.luv2code.springdemo;

public class RandomFortuneService implements FortuneService {

    private static final String[] fortunes = {"Fortune 0", "Fortune 1", "Fortune 2"};

    @Override
    public String getFortune() {
        return fortunes[(int)(Math.random() * fortunes.length)];
    }
}
