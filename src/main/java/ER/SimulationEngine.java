package ER;

import java.io.FileWriter;
import java.util.PriorityQueue;
import java.util.Random;

public class SimulationEngine {

    public EDQueue queue = new EDQueue();
    private PriorityQueue<Event> eventQueue = new PriorityQueue<>();

    public Doctor[] doctors;
    public StatsCollector stats;

    private double currentTime = 0;
    private double endTime;

    // parameters
    public double arrivalRate;
    public double serviceRate;
    public int numDoctors;

    private Random rand = new Random();

    public SimulationEngine(double arrivalRate, double serviceRate, int numDoctors, double endTime) {
        this.arrivalRate = arrivalRate;
        this.serviceRate = serviceRate;
        this.numDoctors = numDoctors;
        this.endTime = endTime;

        // Create doctors
        doctors = new Doctor[numDoctors];
        for (int i = 0; i < numDoctors; i++)
            doctors[i] = new Doctor(i + 1);

        stats = new StatsCollector();
    }

    public Doctor getFreeDoctor() {
        for (Doctor d : doctors)
            if (!d.isBusy()) return d;
        return null;
    }

    public void scheduleEvent(Event e) { eventQueue.add(e); }

    public double generateInterarrivalTime() {
        return -Math.log(1 - rand.nextDouble()) / arrivalRate;
    }

    public double generateServiceTime() {
        return -Math.log(1 - rand.nextDouble()) / serviceRate;
    }

    public void runSimulation() {

        // First arrival
        scheduleEvent(new ArrivalEvent(0));

        while (!eventQueue.isEmpty()) {
            Event e = eventQueue.poll();
            if (e.time > endTime) break;

            currentTime = e.time;
            e.execute(this);

            // attempt to start service if doctor free + queue nonempty
            Doctor free = getFreeDoctor();
            if (free != null && !queue.isEmpty()) {

                Patient next = queue.poll();

                free.setBusy(true);
                next.setAssignedDoctor(free);
                next.setServiceStartTime(currentTime);

                stats.recordServiceStart(next, currentTime);

                double end = currentTime + generateServiceTime();
                scheduleEvent(new ServiceEndEvent(end, next));
            }
        }

        stats.finalizeStats(endTime);
    }
}
