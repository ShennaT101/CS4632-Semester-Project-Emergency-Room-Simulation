package ER;

public class Patient implements Comparable<Patient> {

    private static int counter = 0;

    private final int id;
    private final int severity;
    private final double arrivalTime;

    private double serviceStartTime;
    private double departureTime;

    private Doctor assignedDoctor;

    public Patient(int severity, double arrivalTime) {
        this.id = ++counter;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    public int getId() { return id; }
    public int getSeverity() { return severity; }
    public double getArrivalTime() { return arrivalTime; }

    public void setServiceStartTime(double time) { this.serviceStartTime = time; }
    public void setDepartureTime(double time) { this.departureTime = time; }

    public double getWaitTime() { return serviceStartTime - arrivalTime; }

    public void setAssignedDoctor(Doctor doc) { this.assignedDoctor = doc; }
    public Doctor getAssignedDoctor() { return assignedDoctor; }

    @Override
    public int compareTo(Patient other) {
        // LOWER severity number means higher priority (1 = worst)
        return Integer.compare(this.severity, other.severity);
    }

    @Override
    public String toString() {
        return "Patient#" + id + " (sev=" + severity + ")";
    }
}
