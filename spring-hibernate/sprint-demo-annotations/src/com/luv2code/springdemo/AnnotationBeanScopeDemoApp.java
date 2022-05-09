package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeDemoApp {
    public static void main(String[] args) {
        // load spring config file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieve bean from spring container
        // get bean from spring container
        TennisCoach coach1 = context.getBean("tennisCoach", TennisCoach.class);
        TennisCoach coach2 = context.getBean("tennisCoach", TennisCoach.class);

        boolean sameReferences = coach1 == coach2;
        System.out.println("Same reference used: " + sameReferences);

        System.out.println("Address of first object: " + coach1);
        System.out.println("Address of second object: " + coach2);

        System.out.println();
        System.out.println(coach1.getDailyFortune());

        // close the context
        context.close();
    }
}
