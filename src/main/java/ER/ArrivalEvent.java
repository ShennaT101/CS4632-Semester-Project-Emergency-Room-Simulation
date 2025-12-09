package ER;

import java.util.Random;

public class ArrivalEvent extends Event {
    private static final Random rand = new Random();

    public ArrivalEvent(double time) {
        super(time);
    }

    @Override
    public void execute(SimulationEngine engine) {
        // Create patient with random severity
        int severity = rand.nextInt(5) + 1;
        Patient p = new Patient(severity, time);

        engine.queue.add(p);
        engine.stats.recordArrival(p);

        System.out.println("Time " + time + ": Arrival of " + p);

        // If doctor free, schedule immediate service
        if (!engine.doctor.isBusy()) {
            engine.doctor.setBusy(true);
            engine.scheduleEvent(
                    new ServiceEndEvent(time + engine.generateServiceTime(), p)
            );
        }

        // schedule next arrival
        engine.scheduleEvent(new ArrivalEvent(time + engine.generateInterarrivalTime()));
    }
}
