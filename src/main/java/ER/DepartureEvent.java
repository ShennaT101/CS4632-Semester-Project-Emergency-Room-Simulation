package ER;

public class DepartureEvent extends Event {
    private final Patient patient;

    public DepartureEvent(double time, Patient patient) { super(time); this.patient = patient; }

    @Override
    public void execute(SimulationEngine engine) {
        engine.stats.recordDeparture(patient, time);
        System.out.printf("Time %.3f: Departure %s%n", time, patient);
    }
}
