public class Keypad {

    private String entry = "";
    
    //return true for green LED, false for red
    public void newKey(String k){
        if(entry.length() < 4){
            entry = entry + k;
        }
    }

    public String getEntry(){
        String temp = entry;
        entry = "";
        return temp;
    }

    public void clear(){
        entry = "";
    }
    
}
