package com.luv2code.springdemo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

@Component
public class DatabaseService implements FortuneService {

    private final ArrayList<String> fortunes = new ArrayList<>();
    private final Random random = new Random();

    @PostConstruct
    public void setDatabase() {
        String path = "/Users/sergiu/Programming/ANUL-4/Udemy/Thales-Udemy-Training/spring-hibernate/sprint-demo-annotations/src/fortunes.txt";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))) {
            String line;
            while((line = reader.readLine()) != null) {
                fortunes.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getFortune() {
        return fortunes.get(random.nextInt(fortunes.size()));
    }
}
