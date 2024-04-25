package utils;

public class Utilities {
    public static String toTitleCase(String input) {
        String[] words = input.split(" ");
        StringBuilder titleCase = new StringBuilder();
    
        for (String word : words) {
            if (word.length() > 0) {
                titleCase.append(Character.toUpperCase(word.charAt(0)));
                if (word.length() > 1) {
                    titleCase.append(word.substring(1).toLowerCase());
                }
                titleCase.append(" ");
            }
        }
    
        return titleCase.toString().trim();  // Convert StringBuilder back to String and remove trailing space
    }
}
