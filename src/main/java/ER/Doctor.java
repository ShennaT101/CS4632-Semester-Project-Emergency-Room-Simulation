package ER;

public class Doctor {

    private final int id;
    private boolean busy = false;

    public Doctor(int id) {
        this.id = id;
    }

    public boolean isBusy() { return busy; }
    public void setBusy(boolean b) { busy = b; }
    public int getId() { return id; }

    @Override
    public String toString() {
        return "Doctor#" + id + (busy ? "(busy)" : "(free)");
    }
}
