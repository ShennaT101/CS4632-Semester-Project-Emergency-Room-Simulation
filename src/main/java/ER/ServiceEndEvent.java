package ER;

public class ServiceEndEvent extends Event {
    private Patient patient;

    public ServiceEndEvent(double time, Patient patient) {
        super(time);
        this.patient = patient;
    }

    @Override
    public void execute(SimulationEngine engine) {
        System.out.println("Time " + time + ": " + patient + " finished service.");

        engine.stats.recordDeparture(patient, time);

        // Check if queue has more patients
        if (!engine.queue.isEmpty()) {
            Patient next = engine.queue.poll();
            engine.scheduleEvent(new ServiceEndEvent(
                    time + engine.generateServiceTime(), next
            ));
        } else {
            engine.doctor.setBusy(false);
        }
    }
}
