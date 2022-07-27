public class LEDs {

    public String status = "";
    
    public void pass() {
        status = "green";
    }

    public void fail(){
        status = "red";
    }

    public void needPower(boolean goodPercent){
        status = "yellow";
    }
}
