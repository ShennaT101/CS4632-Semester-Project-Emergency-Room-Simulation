package ER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StatsCollector gathers and summarizes performance metrics
 * for a simulation run.
 *
 * <p>Metrics tracked include:
 * <ul>
 *   <li>Total arrivals and departures</li>
 *   <li>Patient waiting times</li>
 *   <li>Patient service times</li>
 *   <li>Doctor utilization (busy minutes)</li>
 * </ul>
 *
 * <p>This class supports both real-time data collection
 * and post-run aggregation.
 */
public class StatsCollector {

    private int totalArrivals = 0;
    private int totalDepartures = 0;

    private final List<Double> waitTimes = new ArrayList<>();
    private final List<Double> serviceTimes = new ArrayList<>();

    /** Accumulated busy time per doctor (minutes) */
    private final Map<String, Double> doctorBusy = new HashMap<>();

    /**
     * Resets all collected statistics.
     */
    public void reset() {
        totalArrivals = 0;
        totalDepartures = 0;
        waitTimes.clear();
        serviceTimes.clear();
        doctorBusy.clear();
    }

    /**
     * Records a patient arrival.
     *
     * @param p arriving patient
     */
    public void recordArrival(Patient p) {
        totalArrivals++;
    }

    /**
     * Records the start of service for a patient.
     *
     * @param p         patient
     * @param startTime service start time
     */
    public void recordServiceStart(Patient p, double startTime) {
        waitTimes.add(startTime - p.getArrivalTime());
    }

    /**
     * Records the completion of service for a patient.
     *
     * @param p         patient
     * @param startTime service start time
     * @param endTime   service end time
     */
    public void recordServiceEnd(Patient p, double startTime, double endTime) {
        serviceTimes.add(endTime - startTime);
    }

    /**
     * Records a patient departure.
     *
     * @param p    patient
     * @param time departure time
     */
    public void recordDeparture(Patient p, double time) {
        totalDepartures++;
    }

    /**
     * Adds accumulated busy time for a doctor.
     *
     * @param doctorId doctor identifier
     * @param minutes  busy time in minutes
     */
    public void addDoctorBusy(String doctorId, double minutes) {
        doctorBusy.merge(doctorId, minutes, Double::sum);
    }

    /**
     * Prints a summary of statistics to the console.
     */
    public void finalizeStats() {
        double avgWait = waitTimes.stream().mapToDouble(d -> d).average().orElse(0.0);
        double avgService = serviceTimes.stream().mapToDouble(d -> d).average().orElse(0.0);

        System.out.println("---- Simulation Summary ----");
        System.out.println("Total arrivals: " + totalArrivals);
        System.out.println("Total departures: " + totalDepartures);
        System.out.printf("Average wait: %.3f minutes%n", avgWait);
        System.out.printf("Average service: %.3f minutes%n", avgService);

        doctorBusy.forEach(
                (k, v) -> System.out.printf("Doctor %s busy minutes: %.3f%n", k, v)
        );
    }

    /**
     * Returns summary statistics as a key-value map.
     *
     * @return summary map
     */
    public Map<String, String> asSummaryMap() {
        Map<String, String> m = new HashMap<>();
        m.put("total_arrivals", Integer.toString(totalArrivals));
        m.put("total_departures", Integer.toString(totalDepartures));
        m.put("avg_wait", String.format("%.4f",
                waitTimes.stream().mapToDouble(d -> d).average().orElse(0.0)));
        m.put("avg_service", String.format("%.4f",
                serviceTimes.stream().mapToDouble(d -> d).average().orElse(0.0)));

        for (Map.Entry<String, Double> e : doctorBusy.entrySet()) {
            m.put(
                    "doctor_" + e.getKey() + "_busy_minutes",
                    String.format("%.3f", e.getValue())
            );
        }
        return m;
    }

    /**
     * Returns the average waiting time safely.
     *
     * @return average wait time (minutes)
     */
    public double getAverageWaitSafe() {
        return waitTimes.stream().mapToDouble(d -> d).average().orElse(0.0);
    }
}
