package ER;

import java.util.ArrayList;
import java.util.List;

public class StatsCollector {
    private int totalArrivals = 0;
    private int totalServed = 0;

    private double totalWaitTime = 0;
    private double totalServiceTime = 0;

    private List<Double> waitTimes = new ArrayList<>();
    private List<Double> serviceTimes = new ArrayList<>();


    // Called when patient arrives
    public void recordArrival(Patient p) {
        totalArrivals++;
    }

    // Called when service begins
    public void recordServiceStart(Patient p, double currentTime) {
        double wait = currentTime - p.getArrivalTime();
        waitTimes.add(wait);
        totalWaitTime += wait;
    }

    // Called when service finishes
    public void recordServiceEnd(Patient p, double startTime, double endTime) {
        double service = endTime - startTime;
        serviceTimes.add(service);
        totalServiceTime += service;
        totalServed++;
    }

    // Called after simulation ends
    public void finalizeStats() {
        System.out.println("---- Simulation Summary ----");
        System.out.println("Total Arrivals: " + totalArrivals);
        System.out.println("Total Served: " + totalServed);

        if (totalServed > 0) {
            System.out.println("Average Wait Time: " + (totalWaitTime / totalServed));
            System.out.println("Average Service Time: " + (totalServiceTime / totalServed));
        }
    }

    // Accessors if needed for exporting CSV later
    public int getTotalArrivals() { return totalArrivals; }
    public int getTotalServed() { return totalServed; }
    public double getAverageWaitTime() { return totalServed > 0 ? totalWaitTime / totalServed : 0; }
    public double getAverageServiceTime() { return totalServed > 0 ? totalServiceTime / totalServed : 0; }
}
