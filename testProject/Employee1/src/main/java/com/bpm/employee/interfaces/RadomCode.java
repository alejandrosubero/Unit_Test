package com.bpm.employee.interfaces;

import java.util.Random;
import java.util.UUID;

public interface RadomCode {

   public  default  String generateCode(String cod) {
        String code = "UW-" + cod + "*" + UUID.randomUUID().toString();
        return code;
    }

    public default String generateCodeLikeNumber(int len, Integer combination){
        String values ="";
        String capitalChars ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String smallChars = "abcdefghijklmnoprstuvwxyx";
        String numbers = "0123456789";
        String symbols = "*!@#$%^&*()_+=/.?<>";

        if(combination == 1){
            values = capitalChars + smallChars +numbers;
        } else if(combination == 2){
            values = capitalChars + numbers;
        } else if(combination == 3){
            values = numbers;
        }else {
            values = capitalChars + smallChars +numbers+symbols;
        }

        Random rndm_method = new Random();
        char[] charArray = new char[len];
        for(int i =0; i<len; i++){
            charArray [i] = values.charAt(rndm_method.nextInt(values.length()));
        }
        return String.valueOf(charArray);
    }

    public default Integer generateRandomNumber(Integer minNumber, Integer maxNumber) {
        Integer b = 0;
        if(minNumber > maxNumber){
            b = new Random().nextInt( minNumber  - maxNumber + 1) + maxNumber;
        }else {
            b = new Random().nextInt(maxNumber - minNumber + 1) + minNumber;
        }
        return b;
    }

    public default Integer generateRandomNumber(Integer maxNumber) {
        return new Random().nextInt(maxNumber);
    }

    public default  long generatePositiveRandomLong() {
        long randomNumber = new Random().nextLong();
        return Math.abs(randomNumber);
    }

}
