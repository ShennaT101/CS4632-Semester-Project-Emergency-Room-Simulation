package ER;

import java.util.Random;

/**
 * ArrivalEvent represents the arrival of a new patient into the Emergency Room.
 *
 * <p>This event is responsible for:
 * <ul>
 *   <li>Creating a new patient with a randomly assigned severity level</li>
 *   <li>Adding the patient to the waiting queue</li>
 *   <li>Recording arrival statistics</li>
 *   <li>Immediately assigning the patient to a free doctor if available</li>
 *   <li>Scheduling the next patient arrival event</li>
 * </ul>
 *
 * <p>This class is a core component of the discrete-event simulation engine
 * and models stochastic patient arrivals into the ER system.
 */
public class ArrivalEvent extends Event {

    /** Random number generator used to assign patient severity levels */
    private static final Random rand = new Random();

    /**
     * Constructs a new ArrivalEvent at the specified simulation time.
     *
     * @param time the simulation time at which the patient arrives
     */
    public ArrivalEvent(double time) {
        super(time);
    }

    /**
     * Executes the arrival event.
     *
     * <p>Execution steps include:
     * <ol>
     *   <li>Generate a new patient with random severity</li>
     *   <li>Add the patient to the queue</li>
     *   <li>Record arrival statistics</li>
     *   <li>Assign the patient to a free doctor if one is available</li>
     *   <li>Schedule the next arrival event</li>
     * </ol>
     *
     * @param engine the simulation engine controlling the event execution
     */
    @Override
    public void execute(SimulationEngine engine) {

        // Generate patient severity level (1 = least severe, 5 = most severe)
        int severity = rand.nextInt(5) + 1;

        // Create a new patient arriving at the current simulation time
        Patient p = new Patient(severity, time);

        // Add patient to the waiting queue
        engine.queue.add(p);

        // Record arrival statistics
        engine.stats.recordArrival(p);

        // Log arrival event for debugging and traceability
        System.out.println("Time " + time + ": Arrival of " + p);

        // Check if a doctor is immediately available
        Doctor freeDoc = engine.getFreeDoctor();
        if (freeDoc != null) {

            // Assign patient to the available doctor
            freeDoc.setBusy(true, time);
            p.setAssignedDoctor(freeDoc);
            p.setServiceStart(time);

            // Record service start statistics
            engine.stats.recordServiceStart(p, time);

            // Schedule service completion event
            double end = time + engine.generateServiceTime();
            engine.scheduleEvent(new ServiceEndEvent(end, p));
        }

        // Schedule the next patient arrival based on interarrival distribution
        engine.scheduleEvent(
                new ArrivalEvent(time + engine.generateInterarrivalTime())
        );
    }
}
