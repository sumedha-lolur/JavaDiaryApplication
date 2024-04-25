package diary;

import java.util.Date;
import java.util.*; // Import the ArrayList class

public class DiaryEntry {
    private Date date;
    private String text;
    private String title;
    private String mood;

    private String storedDate;
    private String storedText;
    private String storedTitle;
    private String storedMood;

    public DiaryEntry(Date date, String title, String mood, String text) {
        System.out.println(mood);
        this.date = date;
        this.text = text;
        this.title = title;
        this.mood = mood;
    }

    public DiaryEntry(String date, String title, String mood, String text) {
        this.storedDate = date;
        this.storedText = text;
        this.storedTitle = title;
        this.storedMood = mood;
    }

    public Date getDate() {
        return date;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void displayEntry() {
        System.out.println("Date: " + date);
        System.out.println("Title: " + title);
        System.out.println("Mood: " + mood);
        System.out.println("\nDear Diary,\n " + text);  
    }
    public void displayEntry(String date) {
        System.out.println("Date: " + storedDate);
        System.out.println("Title: " + storedTitle);
        System.out.println("Mood: " + storedMood);
        System.out.println("\nDear Diary,\n " + storedText);  
    }
}
