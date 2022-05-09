package com.demo.exercise;

public class TestCoach implements Coach {

    private FortuneService fortuneService;

    public TestCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "This is a test daily workout implementation";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
