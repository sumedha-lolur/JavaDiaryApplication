import diary.*;
import user.*;
import java.util.Date;
import java.util.Scanner;
import utils.*;
import java.text.SimpleDateFormat;
import database.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        database.ConnectToDatabase db = new database.ConnectToDatabase();
        db.connect();
        while (true) {
            System.out.println("Enter \n1) to create a diary entry, " +
                    "\n2) to save current entry, " +
                    "\n3) to read an entry, " +
                    "\n4) to modify an entry, " +
                    "\n5) to delete an entry," +
                    "\n6) to enter private mode,"+
                    "\nor \n0) to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline left
            switch (choice) {
                case 1:
                    System.out.println("Enter the date for the entry (dd/MM):");
                    String dateStr = scanner.nextLine();
                    Date date = null;
                    try {
                        date = format.parse(dateStr + "/2024");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please try again.");
                        continue;
                    }
                    System.out.println("Enter the title for the entry:");
                    String title = scanner.nextLine();
                    title = Utilities.toTitleCase(title);

                    System.out.println("How are you feeling today? Happy or sad?");
                    String mood = scanner.nextLine();
                    // System.out.println(mood);
                    mood = Utilities.toTitleCase(mood);
                    System.out.println("Enter the text for the entry:");
                    String text = scanner.nextLine();

                    DiaryEntry entry = new DiaryEntry(date, title, mood, text);
                    user.getDiary().addEntry(entry);
                    break;

                case 2:
                    DiaryEntry currentEntry = user.getDiary().getCurrentEntry();
                    if (currentEntry != null) {
                        Create cr = new Create();
                        cr.saveEntry(currentEntry);
                    } else {
                        System.out.println("No entry available for saving. Please create an entry to save.");
                    }
                    break;

                case 3:
                    System.out.println("Enter the date of the entry you want to read (dd/MM):");
                    dateStr = scanner.nextLine();
                    date = null;
                    try {
                        date = format.parse(dateStr + "/2024");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please try again.");
                        continue;
                    }
                    dateStr = sdf.format(date);
                    // System.out.println(dateStr);
                    Read rd = new Read();
                    rd.getEntriesByDate(dateStr);
                    break;
                
                case 4:
                    System.out.println("Enter the date of the entry you want to modify (dd/MM):");
                    dateStr = scanner.nextLine();
                    date = null;
                    try {
                        date = format.parse(dateStr + "/2024");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please try again.");
                        continue;
                    }
                    dateStr = sdf.format(date);
                    Update up = new Update();
                    up.updateEntry(dateStr);
                    break;
                    
                case 5:
                    System.out.println("Enter the date of the entry you want to delete (dd/MM):");
                    dateStr = scanner.nextLine();
                    date = null;
                    try {
                        date = format.parse(dateStr + "/2024");
                    } catch (Exception e) {
                        System.out.println("Invalid date format. Please try again.");
                        continue;
                    }
                    Delete dl = new Delete();

                    // Assuming 'date' is an instance of Date
                    dateStr = sdf.format(date);
                    System.out.println(dateStr);
                    dl.deleteEntry(dateStr); 
                    break;

                case 6:
                    //<private mode code>//
                    break;

                case 0:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
