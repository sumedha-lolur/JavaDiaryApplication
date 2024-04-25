package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import diary.DiaryEntry;
import utils.Private;
import java.util.Scanner;
public class Update extends Private {
    public void updateEntry(String date) {
        String checkSql = "SELECT * FROM DIARY WHERE DATE = ?";
        String updateSql = "UPDATE DIARY SET TEXT = ? WHERE DATE = ?";

        try (Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary_db", "root", password);
                PreparedStatement checkPstmt = c.prepareStatement(checkSql);
                PreparedStatement updatePstmt = c.prepareStatement(updateSql)) {

            checkPstmt.setString(1, date);
            ResultSet rs = checkPstmt.executeQuery();

            if (rs.next()) {
                Boolean existingPrivacy = rs.getBoolean("privacy");
                if (!existingPrivacy) {
                    // If privacy is false, allow update
                    System.out.println("Please enter the new text for the entry:");
                    Scanner sc = new Scanner(System.in);
                    String newText = sc.nextLine();
                    updatePstmt.setString(1, newText);
                    //sc.nextLine();
                    updatePstmt.setString(2, date);

                    int updatedRows = updatePstmt.executeUpdate();

                    if (updatedRows > 0) {
                        System.out.println("Entry updated successfully.");
                    } else {
                        System.out.println("Update failed.");
                    }
                    //sc.close();
                } else {
                    System.out.println("Entry is private. Cannot update. Please try again");
                }
            } else {
                System.out.println("No entry found for the given date.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
