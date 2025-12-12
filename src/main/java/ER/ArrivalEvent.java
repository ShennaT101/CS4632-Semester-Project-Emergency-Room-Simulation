package ER;

import java.util.Random;

public class ArrivalEvent extends Event {

    private static final Random rand = new Random();

    public ArrivalEvent(double time) {
        super(time);
    }

    @Override
    public void execute(SimulationEngine engine) {

        int severity = rand.nextInt(5) + 1;
        Patient p = new Patient(severity, time);

        engine.queue.add(p);
        engine.stats.recordArrival(p);

        System.out.println("Time " + time + ": Arrival of " + p);

        // Assign immediately if any doctor is available
        Doctor freeDoc = engine.getFreeDoctor();
        if (freeDoc != null) {

            freeDoc.setBusy(true);
            p.setAssignedDoctor(freeDoc);
            p.setServiceStartTime(time);

            engine.stats.recordServiceStart(p, time);

            double end = time + engine.generateServiceTime();
            engine.scheduleEvent(new ServiceEndEvent(end, p));
        }

        // schedule next arrival
        engine.scheduleEvent(new ArrivalEvent(time + engine.generateInterarrivalTime()));
    }
}
