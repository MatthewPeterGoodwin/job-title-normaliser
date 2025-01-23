package com.mpg.normaliser;

import java.util.List;

/**
 * Class to normalise job titles
 */
public class Normaliser {

    // List of normalized job titles
    private static final List<String> NORMALIZED_TITLES = List.of(
            "Architect",
            "Software engineer",
            "Quantity surveyor",
            "Accountant"
    );

    /**
     * Normalizes a job title
     *
     * @param inputTitle the input job title to normalize
     * @return the normalized title
     */
    public String normalise(String inputTitle){
        if (inputTitle == null || inputTitle.trim().isEmpty()) {
            return "";
        }

        String normalizedTitle = "";
        int lowestDistance = Integer.MAX_VALUE;

        for (String title : NORMALIZED_TITLES){
            int distance = calculateDistance(title.toLowerCase(),inputTitle.toLowerCase());
            if (distance < lowestDistance){
                lowestDistance = distance;
                normalizedTitle = title;
            }
        }
        return normalizedTitle;
    }

    /**
     * Calculates the Levenshtein distance between two strings
     *
     * <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">...</a>
     *
     * @param a first string to compare distance
     * @param b second string to compare distance
     */
    public int calculateDistance (String a, String b){
        a = a.toLowerCase();
        b = b.toLowerCase();
        // i == 0
        int [] costs = new int [b.length() + 1];
        for (int j = 0; j < costs.length; j++)
            costs[j] = j;
        for (int i = 1; i <= a.length(); i++) {
            costs[0] = i;
            int nw = i - 1;
            for (int j = 1; j <= b.length(); j++) {
                int cj = Math.min(1 + Math.min(costs[j], costs[j - 1]), a.charAt(i - 1) == b.charAt(j - 1) ? nw : nw + 1);
                nw = costs[j];
                costs[j] = cj;
            }
        }
        return costs[b.length()];
    }


}