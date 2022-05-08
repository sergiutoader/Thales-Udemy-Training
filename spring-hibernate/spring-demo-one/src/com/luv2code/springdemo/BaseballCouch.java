package com.luv2code.springdemo;

public class BaseballCouch implements Coach {

    private FortuneService fortuneService;

    public BaseballCouch(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes on batting practice";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

}
