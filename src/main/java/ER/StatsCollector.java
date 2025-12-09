package ER;

import java.util.ArrayList;

public class StatsCollector {
    private ArrayList<Double> waitTimes = new ArrayList<>();

    public void recordArrival(Patient p) {}

    public void recordDeparture(Patient p, double departureTime) {
        double wait = departureTime - p.getArrivalTime();
        waitTimes.add(wait);
    }

    public void printReport() {
        double avg = waitTimes.stream().mapToDouble(a -> a).average().orElse(0);
        System.out.println("=== Simulation Results ===");
        System.out.println("Average wait time: " + avg);
    }
}
