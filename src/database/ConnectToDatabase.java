package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import utils.Private;
import diary.DiaryEntry;

public class ConnectToDatabase extends Private{
    public void connect() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary_db", 
            "root", password);
            System.out.println("Connected to MySQL server successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS DIARY" +
                         "(ID INT PRIMARY KEY     AUTO_INCREMENT," +
                         " DATE           VARCHAR(100)    NOT NULL UNIQUE, " + 
                         " TITLE          TEXT    NOT NULL, " + 
                         " MOOD           TEXT    NOT NULL, " + 
                         " TEXT           TEXT    NOT NULL, " +
                         " PRIVACY        BOOLEAN NOT NULL DEFAULT FALSE)"; 
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("*******Welcome to Dear Diary**********");
    }
}

interface PrivateCreate {
    void savePrivateEntry(DiaryEntry entry);
}
interface PrivateRead {
    void readPrivateEntry(String date);
}
interface PrivateDelete {
    void deletePrivateEntry(String date);
}