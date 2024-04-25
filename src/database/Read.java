package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import diary.*;

public class Read{
    public void getEntriesByDate(String dateStr){
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary_db", 
            "root", "weare1619");
            System.out.println("Connected to MySQL server successfully");
    
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM DIARY WHERE DATE = '" + dateStr + "';");
            if (!rs.next()) {
                System.out.println("No entry found for the given date. Please try again.");
            } else {
                do {
                    String date = rs.getString("date");
                    String title = rs.getString("title");
                    String mood = rs.getString("mood");
                    String text = rs.getString("text");
                    //Boolean privacy = rs.getBoolean("privacy");
                    DiaryEntry entry = new DiaryEntry(date, title, mood, text);
                    entry.displayEntry(date);
                } while (rs.next());
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void getEntriesByMood(String moodStr) {
        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary_db", "root", "weare1619");
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM DIARY WHERE MOOD = '" + moodStr + "';")) {

            if (!rs.next()) {
                System.out.println("No entry found for the given mood. Please try again.");
            } else {
                do {
                    String date = rs.getString("date");
                    String title = rs.getString("title");
                    String mood = rs.getString("mood");
                    String text = rs.getString("text");
                    //Boolean privacy = rs.getBoolean("privacy");
                    DiaryEntry entry = new DiaryEntry(date, title, mood, text);
                    entry.displayEntry(date);
                } while (rs.next());
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}