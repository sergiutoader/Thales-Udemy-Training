package com.luv2code.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
//@Scope("prototype")
@Scope("singleton")
public class TennisCoach implements Coach {
    // Field dependency injection
    @Autowired
    @Qualifier("databaseService")
    private FortuneService fortuneService;

    @Value("${foo.email}")
    private String email;
    @Value("${foo.team}")
    private String team;

    /*
    @Autowired
    public TennisCoach(FortuneService fortuneService) {
        this.fortuneService = fortuneService;
    }

    */


    public TennisCoach() {
        System.out.println(">> TennisCoach: Inside no-arg constructor");
    }


    /*
    @Autowired
    public void setFortuneService(FortuneService fortuneService) {
        System.out.println(">> TennisCoach: Inside setFortuneService method");
        this.fortuneService = fortuneService;
    }
     */

    // We can use any method name for dependency injection, not just setter or constructor
    /*
    @Autowired
    public void customNameForMethodInjection(FortuneService fortuneService) {
        System.out.println(">> TennisCoach: Inside setFortuneService method");
        this.fortuneService = fortuneService;
    }
     */

    @Override
    public String getDailyWorkout() {
        return "Practice your backhand volley";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }

    public String getTeam() {
        return team;
    }

    public String getEmail() {
        return email;
    }

    @PostConstruct
    public void postConstructMethod() {
        System.out.println(">> Post-construct method called");
    }

    @PreDestroy
    public void preDestryMethod() {
        System.out.println(">> Pre-destroy method called");

    }
}
