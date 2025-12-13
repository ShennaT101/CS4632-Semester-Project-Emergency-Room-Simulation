package ER;

public class Doctor {
    private final String id;
    private boolean busy = false;
    private double busyStart = -1;
    private double busyAccum = 0.0;

    public Doctor(String id) { this.id = id; }

    public boolean isBusy() { return busy; }

    public void setBusy(boolean b, double now) {
        if (!busy && b) { busyStart = now; }
        if (busy && !b) {
            if (busyStart >= 0) busyAccum += (now - busyStart);
            busyStart = -1;
        }
        this.busy = b;
    }

    public void forceIdle(double now) {
        if (busy && busyStart >= 0) busyAccum += (now - busyStart);
        busy = false; busyStart = -1;
    }

    public double getBusyAccum() { return busyAccum; }

    public String getId() { return id; }

    @Override
    public String toString() { return id + (busy ? "(busy)" : "(idle)"); }
}
