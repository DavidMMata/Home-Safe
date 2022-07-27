public class Controller {

    private final String ORIGINALKEY = "5218";

    private boolean resetMode = false;
    private String key = ORIGINALKEY;

    public Door door = new Door();
    public Keypad keypad = new Keypad();
    public LEDs leds = new LEDs();



    public void checkEntry(){
        String entry = keypad.getEntry();
        System.out.println(entry);
        if(entry.equals(key)){
            door.unlock();
            leds.pass();
        }
        else {
            leds.fail();
        }
    }


    public void heldClrReset(){
        resetMode = true;
    }

    public void resetButton(){
        key = ORIGINALKEY;
    }
}
