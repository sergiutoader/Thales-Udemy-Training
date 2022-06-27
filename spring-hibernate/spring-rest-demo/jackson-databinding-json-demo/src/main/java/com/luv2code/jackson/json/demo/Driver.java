package com.luv2code.jackson.json.demo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.Arrays;

public class Driver {

    public static void main(String[] args) {
        try {
            // create object mapper
            ObjectMapper mapper = new ObjectMapper();

            // read JSON file and map/convert to Java POJO
            Student student = mapper.readValue(new File("data/sample-full.json"), Student.class);

            System.out.println("Student first name: " + student.getFirstName());
            System.out.println("Student last name: " + student.getLastName());
            System.out.println("Student id: " + student.getId());
            System.out.println("Student active: " + student.isActive());
            System.out.println("Student street: " + student.getAddress().getStreet());
            System.out.println("Student languages: " + Arrays.toString(student.getLanguages()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
