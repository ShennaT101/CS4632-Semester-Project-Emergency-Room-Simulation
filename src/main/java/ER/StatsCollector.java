package ER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsCollector {
    private int totalArrivals = 0;
    private int totalDepartures = 0;

    private final List<Double> waitTimes = new ArrayList<>();
    private final List<Double> serviceTimes = new ArrayList<>();

    // per-run doctor busy minutes
    private final Map<String, Double> doctorBusy = new HashMap<>();

    public void reset() {
        totalArrivals = 0;
        totalDepartures = 0;
        waitTimes.clear();
        serviceTimes.clear();
        doctorBusy.clear();
    }

    public void recordArrival(Patient p) {
        totalArrivals++;
    }

    public void recordServiceStart(Patient p, double startTime) {
        double wait = startTime - p.getArrivalTime();
        waitTimes.add(wait);
    }

    public void recordServiceEnd(Patient p, double startTime, double endTime) {
        double service = endTime - startTime;
        serviceTimes.add(service);
    }

    public void recordDeparture(Patient p, double time) {
        totalDepartures++;
    }

    public void addDoctorBusy(String doctorId, double minutes) {
        doctorBusy.merge(doctorId, minutes, Double::sum);
    }

    // Called at end to print
    public void finalizeStats() {
        double avgWait = waitTimes.stream().mapToDouble(d -> d).average().orElse(0.0);
        double avgService = serviceTimes.stream().mapToDouble(d -> d).average().orElse(0.0);

        System.out.println("---- Simulation Summary ----");
        System.out.println("Total arrivals: " + totalArrivals);
        System.out.println("Total departures: " + totalDepartures);
        System.out.printf("Average wait: %.3f minutes%n", avgWait);
        System.out.printf("Average service: %.3f minutes%n", avgService);
        doctorBusy.forEach((k, v) -> System.out.printf("Doctor %s busy minutes: %.3f%n", k, v));
    }

    public Map<String,String> asSummaryMap() {
        Map<String,String> m = new HashMap<>();
        m.put("total_arrivals", Integer.toString(totalArrivals));
        m.put("total_departures", Integer.toString(totalDepartures));
        m.put("avg_wait", String.format("%.4f", waitTimes.stream().mapToDouble(d -> d).average().orElse(0.0)));
        m.put("avg_service", String.format("%.4f", serviceTimes.stream().mapToDouble(d -> d).average().orElse(0.0)));
        for (Map.Entry<String, Double> e : doctorBusy.entrySet()) {
            m.put("doctor_"+e.getKey()+"_busy_minutes", String.format("%.3f", e.getValue()));
        }
        return m;
    }

    public double getAverageWaitSafe() {
        return waitTimes.stream().mapToDouble(d -> d).average().orElse(0.0);
    }
}
