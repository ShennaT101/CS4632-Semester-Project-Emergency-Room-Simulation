package ER;

public class Patient implements Comparable<Patient> {
    private static int counter = 0;
    private final int id;
    private final int severity; // 1..5 (1 highest priority)
    private final double arrivalTime;

    private double serviceStart = -1;
    private double serviceEnd = -1;
    private Doctor assignedDoctor = null;

    public Patient(int severity, double arrivalTime) {
        this.id = ++counter;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    public int getId() { return id; }
    public int getSeverity() { return severity; }
    public double getArrivalTime() { return arrivalTime; }

    public void setServiceStart(double t) { this.serviceStart = t; }
    public void setServiceEnd(double t) { this.serviceEnd = t; }

    public double getWaitTime() { return serviceStart < 0 ? -1 : serviceStart - arrivalTime; }
    public double getServiceTime() { return (serviceEnd < 0 || serviceStart < 0) ? -1 : serviceEnd - serviceStart; }

    public double getServiceEnd() {
        return serviceEnd;
    }

    public double getServiceStart() {
        return serviceStart;
    }

    public void setAssignedDoctor(Doctor d) { assignedDoctor = d; }
    public Doctor getAssignedDoctor() { return assignedDoctor; }



    @Override
    public String toString() { return "Patient#" + id + " (sev=" + severity + ")"; }

    @Override
    public int compareTo(Patient o) { return Integer.compare(this.severity, o.severity); }
}
