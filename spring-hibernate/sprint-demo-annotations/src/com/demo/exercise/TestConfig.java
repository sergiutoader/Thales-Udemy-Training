package com.demo.exercise;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
public class TestConfig {

    // define bean for our sad fortune service
    @Bean
    public FortuneService testFortuneService() {
        return new TestFortuneService();
    }

    // define bean for our swim coach and inject dependency
    @Bean
    public Coach testCoach() {
        return new TestCoach(testFortuneService());
    }
}
