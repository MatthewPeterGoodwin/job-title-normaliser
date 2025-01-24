package com.mpg.normaliser;

import java.util.List;

/**
 * Class to normalise job titles
 */
public class Normaliser {

    // List of normalized job titles
    private static final List<String> NORMALIZED_TITLES = List.of("Architect", "Software engineer", "Quantity surveyor", "Accountant");

    /**
     * Normalizes a job title
     *
     * @param inputTitle the input job title to normalize
     * @return the normalized title
     */
    public String normalise(String inputTitle) {
        if (inputTitle == null || inputTitle.trim().isEmpty()) {
            return "";
        }

        String normalizedTitle = "";
        int lowestDistance = Integer.MAX_VALUE;

        for (String title : NORMALIZED_TITLES) {
            int distance = calculateDistance(title.toLowerCase(), inputTitle.toLowerCase());
            if (distance < lowestDistance) { // The lower the distance, the better the match
                lowestDistance = distance;
                normalizedTitle = title; // Match with current lowest distance
            }
        }
        return normalizedTitle;
    }

    /**
     * Calculates the Levenshtein distance between two strings
     * <a href="https://en.wikipedia.org/wiki/Levenshtein_distance">...</a>
     *
     * @param a first string to compare distance
     * @param b second string to compare distance
     */
    public int calculateDistance(String a, String b) {
        int len1 = a.length();
        int len2 = b.length();

        // Create a DP table
        int[][] dp = new int[len1 + 1][len2 + 1];

        // Initialize the table
        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i; // Cost of all deletions
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j; // Cost of all insertions
        }

        // Fill the table
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;

                dp[i][j] = Math.min(dp[i - 1][j] + 1, // Deletion
                        Math.min(dp[i][j - 1] + 1, // Insertion
                                dp[i - 1][j - 1] + cost // Substitution
                        ));
            }
        }
        // The final cell contains the Levenshtein distance
        return dp[len1][len2];
    }
}

