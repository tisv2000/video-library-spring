package com.tisv2000;

public class AppService {
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("This is a service module's method main call");

        var localDate = LocalDateFormatter.format("2022-01-01");

        System.out.println("Today's year is: " + localDate.getYear());
        System.out.println("===========================================");
    }
}
