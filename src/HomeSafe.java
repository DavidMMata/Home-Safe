import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.IOException;

/**
 * Contains the methods to run the visual demo of the safe.
 * Uses javafx to display a digital representation of the physical safe.
 */
public class HomeSafe extends Application {

    Controller controller = new Controller();

    @FXML
    Circle led;
    @FXML
    Circle batteryLED;
    @FXML
    Rectangle doorInside1;
    @FXML
    Line doorInside2;
    @FXML
    Line doorInside3;
    @FXML
    Rectangle openDoor;
    @FXML
    Rectangle handle;
    @FXML
    Button doorBtn;
    Boolean doorOpen = false;

    /**
     * Displays a green LED to indicate success to the user.
     * Uses a timer to blink the led on and off.
     */
    public void passLED() {
        led.setFill(Color.GREEN);
        long end = System.nanoTime()+500000000;
        while(System.nanoTime() < end) {} /*Blink delay*/
        led.setFill(Color.GRAY);
    }


    /**
     * Displays a red LED to indicate failure to the user.
     * Uses a timer to blink the led on and off.
     */
    void failLED() {
        led.setFill(Color.RED);
        long end = System.nanoTime()+500000000;
        while(System.nanoTime() < end) {} /*Blink delay*/
        led.setFill(Color.GRAY);
    }

    /**
     * Displays an orange LED to indicate low battery power.
     * LED remains on until power is no longer low.
     * @param on Boolean value; TRUE if power is low, else FALSE.
     */
    private void lowBatteryLED(boolean on) {
        if(on) batteryLED.setFill(Color.ORANGE);
        else batteryLED.setFill(Color.GRAY);
    }

    /**
     * Changes the visual demo to display the safe in the open state.
     * Checks to make sure the door is unlocked before 'opening' the door.
     */
    private void openDoorDisplay() {
        if(!controller.door.checkLocked()) {
            doorInside1.setVisible(true);
            doorInside2.setVisible(true);
            doorInside3.setVisible(true);
            openDoor.setVisible(true);
            handle.setVisible(false);
            doorBtn.setText("Close Door");
            doorOpen = true;
        } else {
            doorOpen = false;
        }
    }

    /**
     * Changes the visual demo to display the safe in the closed state.
     * Re-locks the door after closing it.
     */
    private void closeDoorDisplay() {
        doorInside1.setVisible(false);
        doorInside2.setVisible(false);
        doorInside3.setVisible(false);
        openDoor.setVisible(false);
        handle.setVisible(true);
        controller.door.lock();
        doorBtn.setText("Open Door");
        doorOpen = true;
    }

    /**
     * Called whenever a number on the keypad is pressed.
     * Retrieves the button which was clicked and the number it
     * corresponds to, passing that value to the keypad controller.
     * Also checks if LEDs need to be updated.
     * @param actionEvent Keypad number button clicked.
     * @throws IOException
     */
    @FXML
    private void keyClicked(ActionEvent actionEvent) throws IOException {
        Button clicked = (Button) actionEvent.getSource();
        String k = clicked.getText();
        controller.keypad.newKey(k);
        checkBlink();
    }

    /**
     * Called whenever the enter button on the keypad is pressed.
     * Tells the controller to check the current entry, then checks
     * if LEDs need to be updated.
     * @param actionEvent Enter button clicked.
     */
    @FXML
    public void enterClicked(ActionEvent actionEvent) {
        controller.checkEntry();
        checkBlink();
    }

    /**
     * Called whenever the clear button on the keypad is pressed.
     * Tells the keypad controller that clear has been pressed.
     * @param actionEvent Clear button clicked.
     */
    @FXML
    public void clearClicked(ActionEvent actionEvent) {
        controller.keypad.clear();
    }

    /**
     * Called when the 'Open Door' or 'Close Door' button has been pressed.
     * Represents the action of pulling the door handle. Calls the close and open
     * door display methods to update the visual demo state.
     * @param actionEvent Toggle door state button clicked.
     */
    @FXML
    public void toggleDoor(ActionEvent actionEvent) {
        passLED();
        if (doorOpen) {
            closeDoorDisplay();
        } else {
            openDoorDisplay();
        }
    }


    /**
     * Checks if the LEDs need to be updated in the visual demo.
     * Grabs the LED status from the LEDs controller and displays the
     * corresponding state.
     */
    private void checkBlink(){
        String status = controller.leds.status;
        switch (status){
            case "green":
                passLED();
            case "red":
                failLED();
            case "yellow":
                lowBatteryLED(true);
            default:
                //nothing
        }
    }

    /**
     * Launches the visual demo.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the visual demo by loading the fxml file and displaying
     * the stage.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Home Safe");

        Parent root = FXMLLoader.load(getClass().getResource("resources/safedemo.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launches the application.
     * @param args
     */
    public static void launchApplication(String args) {
        launch(args);
    }
}
