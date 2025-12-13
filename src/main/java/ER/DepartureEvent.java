package ER;

/**
 * DepartureEvent represents the departure of a patient from the Emergency Room
 * after completing service.
 *
 * <p>This event is responsible for:
 * <ul>
 *   <li>Recording patient departure statistics</li>
 *   <li>Logging the departure event for traceability</li>
 * </ul>
 *
 * <p>Departure events mark the completion of a patient's lifecycle
 * within the simulation.
 */
public class DepartureEvent extends Event {

    /** The patient who is leaving the system */
    private final Patient patient;

    /**
     * Constructs a DepartureEvent for the specified patient.
     *
     * @param time    the simulation time at which the patient departs
     * @param patient the patient leaving the ER
     */
    public DepartureEvent(double time, Patient patient) {
        super(time);
        this.patient = patient;
    }

    /**
     * Executes the departure event.
     *
     * <p>Execution steps include:
     * <ol>
     *   <li>Record patient departure statistics</li>
     *   <li>Output a log message indicating the departure</li>
     * </ol>
     *
     * @param engine the simulation engine controlling event execution
     */
    @Override
    public void execute(SimulationEngine engine) {

        // Record patient departure statistics
        engine.stats.recordDeparture(patient, time);

        // Log departure for debugging and traceability
        System.out.printf("Time %.3f: Departure %s%n", time, patient);
    }
}
