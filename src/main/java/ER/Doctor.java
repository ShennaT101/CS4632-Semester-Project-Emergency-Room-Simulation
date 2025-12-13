package ER;

/**
 * Doctor represents a medical resource in the Emergency Room simulation.
 *
 * <p>Each doctor can serve at most one patient at a time and maintains
 * internal state used to track busy periods and utilization statistics.
 *
 * <p>This class supports:
 * <ul>
 *   <li>Tracking whether the doctor is currently busy</li>
 *   <li>Recording cumulative busy time for utilization calculations</li>
 *   <li>Explicit transitions between busy and idle states</li>
 * </ul>
 */
public class Doctor {

    /** Unique identifier for the doctor */
    private final String id;

    /** Indicates whether the doctor is currently serving a patient */
    private boolean busy = false;

    /** Simulation time when the doctor last became busy */
    private double busyStart = -1;

    /** Accumulated busy time across the simulation */
    private double busyAccum = 0.0;

    /**
     * Constructs a Doctor with the given identifier.
     *
     * @param id unique identifier for the doctor (e.g., \"D1\", \"D2\")
     */
    public Doctor(String id) {
        this.id = id;
    }

    /**
     * Indicates whether the doctor is currently busy.
     *
     * @return {@code true} if the doctor is serving a patient, {@code false} otherwise
     */
    public boolean isBusy() {
        return busy;
    }

    /**
     * Updates the busy/idle state of the doctor.
     *
     * <p>This method correctly tracks busy start and end times in order to
     * accumulate total busy duration for utilization analysis.
     *
     * @param b   new busy state
     * @param now current simulation time
     */
    public void setBusy(boolean b, double now) {

        // Transition from idle to busy
        if (!busy && b) {
            busyStart = now;
        }

        // Transition from busy to idle
        if (busy && !b) {
            if (busyStart >= 0) {
                busyAccum += (now - busyStart);
            }
            busyStart = -1;
        }

        this.busy = b;
    }

    /**
     * Forces the doctor into an idle state.
     *
     * <p>This method is typically used at simulation termination to ensure
     * that any remaining busy time is properly accounted for.
     *
     * @param now current simulation time
     */
    public void forceIdle(double now) {
        if (busy && busyStart >= 0) {
            busyAccum += (now - busyStart);
        }
        busy = false;
        busyStart = -1;
    }

    /**
     * Returns the total accumulated busy time for this doctor.
     *
     * @return total busy time
     */
    public double getBusyAccum() {
        return busyAccum;
    }

    /**
     * Returns the doctor's identifier.
     *
     * @return doctor ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns a human-readable string representation of the doctor.
     *
     * @return string describing doctor state
     */
    @Override
    public String toString() {
        return id + (busy ? "(busy)" : "(idle)");
    }
}
