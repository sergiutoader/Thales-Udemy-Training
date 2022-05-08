package com.luv2code.springdemo;

public class TrackCoach implements Coach {

    private FortuneService fortuneService;

    public TrackCoach() {}

    public TrackCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Run a hard 5k";
    }

    @Override
    public String getDailyFortune() {
        return "Just do it: " + fortuneService.getFortune();
    }

    // add an init and destroy method
    public void doMyStartupStuff() {
        System.out.println("TrackCoach: Inside method doMyStartupStuff");
    }

    public void doMyCleanupStuff() {
        System.out.println("TrackCoach: Inside method doMyCleanupStuff");
    }
}
