package ER;

/**
 * Patient represents an individual arriving at the Emergency Room.
 *
 * <p>Each patient has:
 * <ul>
 *   <li>A unique identifier</li>
 *   <li>A severity level determining service priority</li>
 *   <li>Arrival, service start, and service end times</li>
 *   <li>An assigned doctor (if served)</li>
 * </ul>
 *
 * <p>Patients are comparable by severity to support priority-based queuing.
 */
public class Patient implements Comparable<Patient> {

    /** Global patient counter used to assign unique IDs */
    private static int counter = 0;

    /** Unique patient identifier */
    private final int id;

    /** Severity level (1 = highest priority, 5 = lowest priority) */
    private final int severity;

    /** Simulation time at which the patient arrives */
    private final double arrivalTime;

    /** Time service begins */
    private double serviceStart = -1;

    /** Time service ends */
    private double serviceEnd = -1;

    /** Doctor assigned to the patient */
    private Doctor assignedDoctor = null;

    /**
     * Constructs a new patient.
     *
     * @param severity    severity level (1–5)
     * @param arrivalTime simulation arrival time
     */
    public Patient(int severity, double arrivalTime) {
        this.id = ++counter;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }

    /** @return patient ID */
    public int getId() {
        return id;
    }

    /** @return patient severity */
    public int getSeverity() {
        return severity;
    }

    /** @return arrival time */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Sets the service start time.
     *
     * @param t simulation time service begins
     */
    public void setServiceStart(double t) {
        this.serviceStart = t;
    }

    /**
     * Sets the service end time.
     *
     * @param t simulation time service ends
     */
    public void setServiceEnd(double t) {
        this.serviceEnd = t;
    }

    /**
     * Computes the patient's waiting time.
     *
     * @return waiting time, or -1 if service has not started
     */
    public double getWaitTime() {
        return serviceStart < 0 ? -1 : serviceStart - arrivalTime;
    }

    /**
     * Computes the patient's service time.
     *
     * @return service time, or -1 if incomplete
     */
    public double getServiceTime() {
        return (serviceEnd < 0 || serviceStart < 0)
                ? -1
                : serviceEnd - serviceStart;
    }

    /** @return service end time */
    public double getServiceEnd() {
        return serviceEnd;
    }

    /** @return service start time */
    public double getServiceStart() {
        return serviceStart;
    }

    /**
     * Assigns a doctor to the patient.
     *
     * @param d doctor assigned to the patient
     */
    public void setAssignedDoctor(Doctor d) {
        assignedDoctor = d;
    }

    /** @return assigned doctor */
    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }

    /**
     * Returns a readable string representation of the patient.
     *
     * @return patient description
     */
    @Override
    public String toString() {
        return "Patient#" + id + " (sev=" + severity + ")";
    }

    /**
     * Compares patients by severity for priority ordering.
     *
     * @param o other patient
     * @return comparison result
     */
    @Override
    public int compareTo(Patient o) {
        return Integer.compare(this.severity, o.severity);
    }
}
