package ER;

public class ServiceEndEvent extends Event {

    private final Patient patient;

    public ServiceEndEvent(double time, Patient patient) {
        super(time);
        this.patient = patient;
    }

    @Override
    public void execute(SimulationEngine engine) {
        System.out.println("Time " + time + ": Service ends for " + patient);

        // Schedule departure
        engine.scheduleEvent(new DepartureEvent(time, patient));
    }
}
