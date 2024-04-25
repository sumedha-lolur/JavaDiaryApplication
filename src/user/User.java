package user;
import diary.*;
public class User {
    private Diary diary;
    protected String password = "1234";
    public User() {
        diary = new Diary();
    }

    public Diary getDiary() {
        return diary;
    }
}

