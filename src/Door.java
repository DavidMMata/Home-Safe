public class Door {
    private boolean isLocked = true;

    public void lock(){
        isLocked = true;
    }

    public void unlock(){
        isLocked = false;
    }


    public boolean checkLocked(){
        return isLocked;
    }
}
