package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");

        Coach trackCoach1 = context.getBean("myCoach", Coach.class);
        Coach trackCoach2 = context.getBean("myCoach", Coach.class);

        boolean sameBean = trackCoach1 == trackCoach2;
        System.out.println("Pointing to the same object: " + sameBean);
        context.close();

    }
}
