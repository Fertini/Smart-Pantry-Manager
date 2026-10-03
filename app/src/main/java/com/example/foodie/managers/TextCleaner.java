package com.example.foodie.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TextCleaner {

    private static final Set<String> NOISE = Set.of( //  terms to be removed
            "cup", "tbsp", "tsp", "can", "clove", "g", "kg", "ml", "l", "oz", "lb",
            "pinch", "cooked", "diced", "chopped", "fresh", "large", "small",
            "medium", "of", "sliced", "minced");

    // cleans the string by removing extra words, units of measurements, and numbers
    public static List<String> tokens(String raw) {
        List<String> result = new ArrayList<>();

        String cleaned = raw.toLowerCase().replaceAll("[^a-z\\s]", " "); // remove digits & punctuation

        for (String text : cleaned.trim().split("\\s+")) {
            if (text.isEmpty()) continue;
            text = singular(text);
            if (!NOISE.contains(text)) result.add(text);
        }
        return result;
    }

    // removes suffixes from the ingredient
    private static String singular(String text) {
        if (text.endsWith("ies") && text.length() > 4) return text.substring(0, text.length() - 3) + "y";
        if (text.endsWith("oes") && text.length() > 4) return text.substring(0, text.length() - 2);
        if (text.endsWith("s") && !text.endsWith("ss") && text.length() > 3) return text.substring(0, text.length() - 1);
        return text;
    }

    // checks if the words are a match
    private static boolean wordsMatch(String a, String b) {
        if (a.equals(b)) return true;
        // allow 1 typo, but only for longer words
        return Math.min(a.length(), b.length()) >= 5
                && BasicMatcher.getLevenshteinDistance(a, b) <= 1;
    }

    // returns true if every word of the shorter name appears in the longer one.
    public static boolean namesMatch(String recipeLine, String pantryName) {
        List<String> a = tokens(recipeLine);
        List<String> b = tokens(pantryName);
        if (a.isEmpty() || b.isEmpty()) return false;

        List<String> shorter = a.size() <= b.size() ? a : b;
        List<String> longer  = a.size() <= b.size() ? b : a;

        return shorter.stream().allMatch(s ->
                longer.stream().anyMatch(l -> wordsMatch(s, l)));
    }

}

