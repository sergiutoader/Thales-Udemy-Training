package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        SoccerCoach theSoccerCoach = context.getBean("mySoccerCoach", SoccerCoach.class);
        System.out.println(theSoccerCoach.getDailyWorkout() + "\n");

        System.out.println(theSoccerCoach.getDailyFortune() + "\n");

        System.out.println(theSoccerCoach.getEmailAddress() + " " + theSoccerCoach.getTeam() + "\n");

        context.close();
    }
}
