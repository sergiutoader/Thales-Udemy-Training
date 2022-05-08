package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifecycleDemoApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanLifecycle-applicationContext.xml");

        Coach trackCoach = context.getBean("myCoach", Coach.class);
        System.out.println(trackCoach.getDailyWorkout());

        SoccerCoach soccerCoach1 = context.getBean("mySoccerCoach", SoccerCoach.class);
        SoccerCoach soccerCoach2 = context.getBean("mySoccerCoach", SoccerCoach.class);

        System.out.println(soccerCoach1);
        System.out.println(soccerCoach2);


        context.close();
    }
}
