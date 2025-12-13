package ER;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * SimulationEngine implements the core discrete-event simulation (DES)
 * for the Emergency Room model.
 *
 * <p><b>Time Unit:</b> Minutes
 *
 * <p>This engine manages:
 * <ul>
 *   <li>Event scheduling and execution</li>
 *   <li>Patient arrivals and departures</li>
 *   <li>Doctor resource allocation</li>
 *   <li>Queue handling</li>
 *   <li>Statistical data collection</li>
 * </ul>
 *
 * <p>The simulation proceeds by repeatedly executing the earliest
 * scheduled event until the simulation end time is reached.
 */
public class SimulationEngine {

    /** Emergency department waiting queue */
    public EDQueue queue = new EDQueue();

    /** Future event list (priority queue ordered by event time) */
    private PriorityQueue<Event> eventQueue = new PriorityQueue<>();

    /** Doctor resources */
    public Doctor[] doctors;

    /** Statistics collector */
    public StatsCollector stats;

    /** Current simulation time (minutes) */
    private double currentTime = 0;

    /** Simulation end time (minutes) */
    private double endTime;

    /** Arrival rate (patients per minute) */
    public double arrivalRate;

    /** Service rate (patients per minute) */
    public double serviceRate;

    /** Number of doctors */
    public int numDoctors;

    /** Random number generator */
    private Random rand = new Random();

    /** Run identifier (used for output directory naming) */
    private String runId = "run";

    /**
     * Primary constructor using explicit parameters.
     *
     * @param arrivalRatePerMinute arrival rate (per minute)
     * @param serviceRatePerMinute service rate (per minute)
     * @param numDoctors           number of doctors
     * @param endTimeMinutes       simulation duration (minutes)
     */
    public SimulationEngine(
            double arrivalRatePerMinute,
            double serviceRatePerMinute,
            int numDoctors,
            double endTimeMinutes
    ) {
        this.arrivalRate = arrivalRatePerMinute;
        this.serviceRate = serviceRatePerMinute;
        this.numDoctors = numDoctors;
        this.endTime = endTimeMinutes;

        doctors = new Doctor[numDoctors];
        for (int i = 0; i < numDoctors; i++) {
            doctors[i] = new Doctor("D" + (i + 1));
        }

        stats = new StatsCollector();
    }

    /**
     * Convenience constructor that initializes the engine from a Config object.
     *
     * <p>Automatically converts:
     * <ul>
     *   <li>Arrival rate (per hour → per minute)</li>
     *   <li>Service mean (minutes → rate)</li>
     *   <li>Simulation hours → minutes</li>
     * </ul>
     *
     * @param c configuration object
     */
    public SimulationEngine(Config c) {
        this.arrivalRate = c.arrivalRatePerHour / 60.0;
        this.serviceRate = 1.0 / c.serviceMeanMinutes;
        this.numDoctors = c.numDoctors;
        this.endTime = c.simHours * 60.0;
        this.runId = c.outputDir != null ? c.outputDir : ("run_seed_" + c.randomSeed);

        this.rand = (c.randomSeed >= 0)
                ? new Random(c.randomSeed)
                : new Random();

        doctors = new Doctor[numDoctors];
        for (int i = 0; i < numDoctors; i++) {
            doctors[i] = new Doctor("D" + (i + 1));
        }

        stats = new StatsCollector();
    }

    /**
     * Returns the first available (idle) doctor.
     *
     * @return free doctor, or null if all are busy
     */
    public Doctor getFreeDoctor() {
        for (Doctor d : doctors) {
            if (!d.isBusy()) return d;
        }
        return null;
    }

    /**
     * Schedules a future event.
     *
     * @param e event to schedule
     */
    public void scheduleEvent(Event e) {
        eventQueue.add(e);
    }

    /**
     * Generates an exponential interarrival time.
     *
     * @return interarrival time (minutes)
     */
    public double generateInterarrivalTime() {
        return -Math.log(1 - rand.nextDouble()) / arrivalRate;
    }

    /**
     * Generates an exponential service time.
     *
     * @return service duration (minutes)
     */
    public double generateServiceTime() {
        return -Math.log(1 - rand.nextDouble()) / serviceRate;
    }

    /**
     * Executes a batch of simulation runs.
     *
     * @param configs list of configurations
     * @return list of run results
     * @throws IOException if output writing fails
     */
    public static List<RunResult> runBatch(List<Config> configs) throws IOException {
        List<RunResult> results = new ArrayList<>();
        for (Config c : configs) {
            SimulationEngine engine = new SimulationEngine(c);
            results.add(engine.run());
        }
        return results;
    }

    /**
     * Executes a single simulation run.
     *
     * @return RunResult containing summary data
     */
    public RunResult run() {

        currentTime = 0;
        eventQueue.clear();
        queue = new EDQueue();
        stats.reset();

        // Schedule initial arrival
        scheduleEvent(new ArrivalEvent(0.0));

        // Main event loop
        while (!eventQueue.isEmpty()) {
            Event e = eventQueue.poll();
            if (e == null || e.getTime() > endTime) break;

            currentTime = e.getTime();
            e.execute(this);

            // Attempt to start service for queued patients
            Doctor free = getFreeDoctor();
            while (free != null && !queue.isEmpty()) {
                Patient next = queue.poll();
                if (next == null) break;

                free.setBusy(true, currentTime);
                next.setAssignedDoctor(free);
                next.setServiceStart(currentTime);

                stats.recordServiceStart(next, currentTime);

                double end = currentTime + generateServiceTime();
                scheduleEvent(new ServiceEndEvent(end, next));

                free = getFreeDoctor();
            }
        }

        // Aggregate doctor utilization
        for (Doctor d : doctors) {
            stats.addDoctorBusy(d.getId(), d.getBusyAccum());
        }

        stats.finalizeStats();

        // Write output
        try {
            CSVExporter exporter = new CSVExporter(runId);
            exporter.writeSummary("summary.csv", stats.asSummaryMap());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return new RunResult(runId, stats.asSummaryMap(), null, null);
    }

    /**
     * Minimal CSV exporter (not used by default).
     */
    private void exportSummaryCsv(RunResult r, String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("avg_wait,throughput\n");
            fw.write(
                    r.summary.getOrDefault("avg_wait", "NA") + "," +
                            r.summary.getOrDefault("throughput", "NA") + "\n"
            );
        }
    }
}
