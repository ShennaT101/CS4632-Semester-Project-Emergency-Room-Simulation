package ER;

public class Patient {
    private static int counter = 0;
    private int id;
    private int severity;
    private double arrivalTime;

    public Patient(int severity, double arrivalTime) {
        this.id = ++counter;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    public int getSeverity() { return severity; }
    public int getId() { return id; }
    public double getArrivalTime() { return arrivalTime; }

    @Override
    public String toString() {
        return "Patient " + id + " (sev=" + severity + ")";
    }
}

