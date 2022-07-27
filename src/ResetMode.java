public class ResetMode {

    private String newKey = "";
    public Keypad keypad;
    private String key;


    public ResetMode(Keypad keypad, String key){
        this.keypad = keypad;
        this.key = key;
    }

    public boolean checkEntry(){
        String entry = keypad.getEntry();
        if(entry.equals(key)){

            return true;
        }
        else {
            return false;
        }
    }

    //grab the entered password
    public String submitEntry(){
        return newKey;
    }
}
