package diary;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
public class Diary {
    protected ArrayList<DiaryEntry> entries;
    private DiaryEntry currentEntry = null;
    SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    
    public Diary() {
        entries = new ArrayList<>();
    }

    public void addEntry(DiaryEntry entry) {
        entries.add(entry);
        setCurrentEntry(entry);
    }

    public void setCurrentEntry(DiaryEntry currentEntry){
        this.currentEntry = currentEntry;
    }
    public DiaryEntry getCurrentEntry() {
        return currentEntry;
    }

    public void removeEntry(DiaryEntry entry) {
        entries.remove(entry);
    }

    public DiaryEntry getEntryByDate(Date date) {
        for (DiaryEntry entry : entries) {
            if (entry.getDate().equals(date)) {
                return entry;
            }
        }
        return null;
    }

    public void saveEntry(DiaryEntry entry) {
        entries.add(entry);
    }
    public void displayEntries() {
        for (DiaryEntry entry : entries) {
            entry.displayEntry();
        }
    }
}
