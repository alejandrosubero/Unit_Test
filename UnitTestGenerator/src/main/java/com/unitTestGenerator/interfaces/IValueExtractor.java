package com.unitTestGenerator.interfaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface IValueExtractor {

    default String extractListContent(String input) {
        String regex = "<(.*?)>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

    default String extractMapContent(String input) {
        String regex = "Map<([^,]+),([^>]+)>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String keyType = matcher.group(1).trim();
            String valueType = matcher.group(2).trim();
            return String.format("Key: %s, Value: %s", keyType, valueType);
        } else {
            return "No Map found.";
        }
    }

    default String getKey(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String[] parts = input.split(",");
        if (parts.length < 2) {
            return "";
        }
        String keyPart = parts[0].trim();
        if (keyPart.startsWith("Key: ")) {
            return keyPart.substring("Key: ".length()).trim();
        }
        return "";
    }

    default String getValue(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }
        String[] parts = input.split(",");
        if (parts.length < 2) {
            return "";
        }
        String valuePart = parts[1].trim();
        if (valuePart.startsWith("Value: ")) {
            return valuePart.substring("Value: ".length()).trim();
        }
        return "";
    }


}
