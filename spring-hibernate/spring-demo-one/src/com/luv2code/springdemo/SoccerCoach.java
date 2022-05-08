package com.luv2code.springdemo;

public class SoccerCoach implements Coach {

    private FortuneService fortuneService;
    private String emailAddress;
    private String team;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public SoccerCoach() {
        System.out.println("Inside no-arg constructor");
    }

    public void setFortuneService(FortuneService fortuneService) {
        System.out.println("Inside fortuneService setter");
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Practice on free kicks";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
}
