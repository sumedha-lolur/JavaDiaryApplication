package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import utils.Private;
public class Delete extends Private implements PrivateDelete{
    public void deleteEntry(String date) {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary_db", "root", "weare1619");
            c.setAutoCommit(false);
            System.out.println("Connected to MySQL server successfully");
    
            stmt = c.createStatement();
            String checkSql = "SELECT * FROM DIARY WHERE DATE = '" + date + "'";
            rs = stmt.executeQuery(checkSql);
            
            if (rs.next()) {
                Boolean privacy = rs.getBoolean("privacy");
                if (!privacy) {
                    // If privacy is false, allow deletion
                    String deleteSql = "DELETE FROM DIARY WHERE DATE = '" + date + "'";
                    stmt.executeUpdate(deleteSql);
                    System.out.println("Entry deleted successfully.");
                } else {
                    System.out.println("Cannot delete entry as it is marked private.");
                }
            } else {
                System.out.println("No entry found for the given date.");
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

    public void deletePrivateEntry(String date) {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary_db", "root", password);
            c.setAutoCommit(false);
            System.out.println("Connected to MySQL server successfully");
    
            stmt = c.createStatement();
            String checkSql = "SELECT * FROM DIARY WHERE DATE = '" + date + "' AND PRIVACY = TRUE";
            rs = stmt.executeQuery(checkSql);
            
            if (rs.next()) {
                String deleteSql = "DELETE FROM DIARY WHERE DATE = '" + date + "'";
                stmt.executeUpdate(deleteSql);
                System.out.println("Entry deleted successfully.");
            } else {
                System.out.println("No entry found for the given date.");
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
