package ER;

import java.util.PriorityQueue;
import java.util.Random;

public class SimulationEngine {
    public PriorityQueue<Event> eventList = new PriorityQueue<>();
    public EDQueue queue = new EDQueue();
    public Doctor doctor = new Doctor();
    public StatsCollector stats = new StatsCollector();

    private double endTime;
    private Random rand = new Random();

    public SimulationEngine(double endTime) {
        this.endTime = endTime;
    }

    public void scheduleEvent(Event e) {
        eventList.add(e);
    }

    public double generateInterarrivalTime() {
        return rand.nextDouble() * 3 + 1; // 1–4 minutes
    }

    public double generateServiceTime() {
        return rand.nextDouble() * 5 + 2; // 2–7 minutes
    }

    public void run() {
        scheduleEvent(new ArrivalEvent(0));

        while (!eventList.isEmpty()) {
            Event e = eventList.poll();
            if (e.getTime() > endTime) break;

            e.execute(this);
        }

        stats.printReport();
    }
}
