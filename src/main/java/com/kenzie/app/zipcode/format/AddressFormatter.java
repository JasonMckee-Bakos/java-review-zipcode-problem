package com.kenzie.app.zipcode.format;

// Utility class - class that has static helper methods
// example: Math class : Math.min() or Math.random()

import java.util.HashMap;

public class AddressFormatter {
    // declare properties
    // lookup table - Hashmap
    private static HashMap<String, String> codeTable = new HashMap<>();

    static {
        codeTable.put("ST", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("AVE", "AVENUE");
        codeTable.put("AVE.", "AVENUE");
        codeTable.put("Ave", "AVENUE");
        codeTable.put("Ave.", "AVENUE");
        codeTable.put("RD", "ROAD");
        codeTable.put("RD.", "ROAD");
        codeTable.put("Rd", "ROAD");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("BLVD", "BOULEVARD");
        codeTable.put("BLVD.", "BOULEVARD");
        codeTable.put("Blvd", "BOULEVARD");
        codeTable.put("Blvd.", "BOULEVARD");
        codeTable.put("LN", "LANE");
        codeTable.put("LN.", "LANE");
        codeTable.put("Ln", "LANE");
        codeTable.put("Ln.", "LANE");
    }
    // Initialize the map -- hardcode
    public static void initCodeTable() {
        codeTable = new HashMap<>();

        codeTable.put("ST", "STREET");
        codeTable.put("ST.", "STREET");
        codeTable.put("St", "STREET");
        codeTable.put("St.", "STREET");
        codeTable.put("AVE", "AVENUE");
        codeTable.put("AVE.", "AVENUE");
        codeTable.put("Ave", "AVENUE");
        codeTable.put("Ave.", "AVENUE");
        codeTable.put("RD", "ROAD");
        codeTable.put("RD.", "ROAD");
        codeTable.put("Rd", "ROAD");
        codeTable.put("Rd.", "ROAD");
        codeTable.put("BLVD", "BOULEVARD");
        codeTable.put("BLVD.", "BOULEVARD");
        codeTable.put("Blvd", "BOULEVARD");
        codeTable.put("Blvd.", "BOULEVARD");

    }

    // Replace address string - use the map
    // Example: 123 Main St.
    // Output: 123 Main STREET
    public static String replaceAbbreviations(String input) {
        String resultStr = input;

        // Write replace logic
        // for each entry in HashMap, search key, replace with value
        for (String currentKey: codeTable.keySet()) {
                resultStr = resultStr.replace(currentKey, codeTable.get(currentKey));
        }
        String finalResultStr = resultStr.replace(".", ""); // Removes trailing period from St.
        return finalResultStr;
    }

    public static String formatAddress(String input) {
        return input.toUpperCase();
    }

    public static String formatStreetAddress(String input) {
        return formatAddress(replaceAbbreviations(input));
    }

    public static void main(String[] args) {
        AddressFormatter.initCodeTable();
        System.out.println(AddressFormatter.replaceAbbreviations("123 Main St."));
    }
}
