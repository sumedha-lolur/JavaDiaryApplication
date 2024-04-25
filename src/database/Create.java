package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import diary.DiaryEntry;
import utils.Private;
public class Create extends Private implements PrivateCreate{
    public void saveEntry(DiaryEntry entry) {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary_db", "root", "weare1619");
            c.setAutoCommit(false);
            System.out.println("Connected to MySQL server successfully");

            stmt = c.createStatement();
            String checkSql = "SELECT * FROM DIARY WHERE DATE = '" + entry.getDate() + "'";
            rs = stmt.executeQuery(checkSql);

            if (rs.next()) {
                System.out.println("An entry with the same date already exists. Please try again.");
            } else {
                String insertSql = "INSERT INTO DIARY (DATE,TITLE,MOOD,TEXT) " +
                                   "VALUES ('" + entry.getDate() + "', '" + entry.getTitle() + "', '" + entry.getMood() + "', '" + entry.getText() + "' );"; 
                stmt.executeUpdate(insertSql);
                System.out.println("Entry saved successfully");
            }

            stmt.close();
            c.commit();
            c.close();
        } 
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public void savePrivateEntry(DiaryEntry entry) {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary_db", "root", password);
            c.setAutoCommit(false);
            System.out.println("Connected to MySQL server successfully");

            stmt = c.createStatement();
            String checkSql = "SELECT * FROM DIARY WHERE DATE = '" + entry.getDate() + "'";
            rs = stmt.executeQuery(checkSql);

            if (rs.next()) {
                System.out.println("An entry with the same date already exists. Please try again.");
            } else {
                String insertSql = "INSERT INTO DIARY (DATE,TITLE,MOOD,TEXT,PRIVACY) " +
                                   "VALUES ('" + entry.getDate() + "', '" + entry.getTitle() + "', '" + entry.getMood() + "', '" + entry.getText() + "', TRUE);"; 
                stmt.executeUpdate(insertSql);
                System.out.println("Entry saved successfully");
            }

            stmt.close();
            c.commit();
            c.close();
        } 
        catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }    
}
