package ER;

public class ServiceEndEvent extends Event {
    private final Patient patient;

    public ServiceEndEvent(double time, Patient patient) {
        super(time);
        this.patient = patient;
    }

    @Override
    public void execute(SimulationEngine engine) {
        Doctor doc = patient.getAssignedDoctor();
        patient.setServiceEnd(time);
        engine.stats.recordServiceEnd(patient, patient.getServiceStart(), time);

        if (doc != null) {
            doc.setBusy(false, time);
        }

        System.out.printf("Time %.3f: Service end %s by %s%n", time, patient, doc);

        // schedule departure
        engine.scheduleEvent(new DepartureEvent(time, patient));
    }
}
