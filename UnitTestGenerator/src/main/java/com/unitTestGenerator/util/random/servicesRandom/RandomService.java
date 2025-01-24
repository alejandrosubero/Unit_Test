package com.unitTestGenerator.util.random.servicesRandom;


import java.time.LocalDate;

public interface RandomService {

    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public Integer generateRandomNumber(Integer maxNumber);
    public Integer generateRandomNumber(Integer minNumber, Integer maxNumber);
    public LocalDate generateRandomDate(LocalDate startDate, LocalDate endDate);
    public String generateRandomText(int length);
    public String generateRandomName();
    public LocalDate generateRandomDate();
    public String generateRandomLastName();

}
