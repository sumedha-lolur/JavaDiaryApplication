package user;
//import diary.DiaryEntry;
public class PremiumUser extends User {
    public void addTagToEntry(DiaryEntry entry, String tag) {
        entry.setText(entry.getText() + " Tag: " + tag);
    }
}
class DiaryEntry {
    private String text;
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
}
