package ER;

public class DepartureEvent extends Event {

    private final Patient patient;

    public DepartureEvent(double time, Patient patient) {
        super(time);
        this.patient = patient;
    }

    @Override
    public void execute(SimulationEngine engine) {
        System.out.println("Time " + time + ": " + patient + " DEPARTS system.");
        engine.stats.recordDeparture(patient, time);

        // Free doctor
        Doctor doc = patient.getAssignedDoctor();
        doc.setBusy(false);

        // If queue has more patients, start next service
        Patient next = engine.queue.poll();
        if (next != null) {
            doc.setBusy(true);
            next.setAssignedDoctor(doc);

            double serviceTime = engine.generateServiceTime();
            double endTime = time + serviceTime;

            engine.stats.recordServiceStart(next, time);

            engine.scheduleEvent(new ServiceEndEvent(endTime, next));
        }
    }
}
