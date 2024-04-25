package diary;

class SpecialDiary extends Diary {
    // Method Overriding: displayEntries method is overridden in the child class
    @Override
    public void displayEntries() {
        System.out.println("Displaying entries from Special Diary:");
        super.displayEntries();
    }
    public void enterPrivateMode(){
        
    }
}
