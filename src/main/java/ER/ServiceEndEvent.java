package ER;

/**
 * ServiceEndEvent represents the completion of service for a patient.
 *
 * <p>This event:
 * <ul>
 *   <li>Records the patient's service end time</li>
 *   <li>Updates statistics</li>
 *   <li>Releases the assigned doctor</li>
 *   <li>Schedules a departure event</li>
 * </ul>
 *
 * <p>Service completion frees system resources and enables
 * queued patients to be served.
 */
public class ServiceEndEvent extends Event {

    /** Patient whose service has completed */
    private final Patient patient;

    /**
     * Constructs a service end event.
     *
     * @param time    simulation time service ends
     * @param patient patient completing service
     */
    public ServiceEndEvent(double time, Patient patient) {
        super(time);
        this.patient = patient;
    }

    /**
     * Executes the service completion logic.
     *
     * <p>Updates patient records, frees the doctor,
     * logs statistics, and schedules a departure event.
     *
     * @param engine simulation engine
     */
    @Override
    public void execute(SimulationEngine engine) {

        Doctor doc = patient.getAssignedDoctor();

        // Record service completion
        patient.setServiceEnd(time);
        engine.stats.recordServiceEnd(
                patient,
                patient.getServiceStart(),
                time
        );

        // Release doctor
        if (doc != null) {
            doc.setBusy(false, time);
        }

        System.out.printf(
                "Time %.3f: Service end %s by %s%n",
                time,
                patient,
                doc
        );

        // Schedule patient departure
        engine.scheduleEvent(new DepartureEvent(time, patient));
    }
}
