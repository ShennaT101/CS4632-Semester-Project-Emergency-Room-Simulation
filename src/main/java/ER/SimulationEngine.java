package ER;

import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * Core DES engine. Time unit = minutes in this implementation.
 */
public class SimulationEngine {

    public EDQueue queue = new EDQueue();
    private PriorityQueue<Event> eventQueue = new PriorityQueue<>();

    public Doctor[] doctors;
    public StatsCollector stats;

    private double currentTime = 0;
    private double endTime;

    // parameters (time unit: minutes)
    public double arrivalRate;   // arrivals per minute
    public double serviceRate;   // service rate per minute
    public int numDoctors;

    private Random rand = new Random();

    // optional run identifier (from Config.outputDir or similar)
    private String runId = "run";

    // Primary constructor
    public SimulationEngine(double arrivalRatePerMinute, double serviceRatePerMinute, int numDoctors, double endTimeMinutes) {
        this.arrivalRate = arrivalRatePerMinute;
        this.serviceRate = serviceRatePerMinute;
        this.numDoctors = numDoctors;
        this.endTime = endTimeMinutes;

        // Create doctors
        doctors = new Doctor[numDoctors];
        for (int i = 0; i < numDoctors; i++) {
            doctors[i] = new Doctor("D" + (i + 1));
        }

        stats = new StatsCollector();
    }

    // Convenience constructor from your Config class (uses field names from your Config)
    public SimulationEngine(Config c) {
        // convert arrival rate per hour -> per minute
        this.arrivalRate = c.arrivalRatePerHour / 60.0;
        // service mean is minutes -> service rate per minute
        this.serviceRate = 1.0 / c.serviceMeanMinutes;
        this.numDoctors = c.numDoctors;
        // convert sim hours -> minutes
        this.endTime = c.simHours * 60.0;
        this.runId = c.outputDir != null ? c.outputDir : ("run_seed_" + c.randomSeed);

        this.rand = (c.randomSeed >= 0) ? new Random(c.randomSeed) : new Random();

        doctors = new Doctor[numDoctors];
        for (int i = 0; i < numDoctors; i++) doctors[i] = new Doctor("D" + (i + 1));

        stats = new StatsCollector();
    }

    public Doctor getFreeDoctor() {
        for (Doctor d : doctors) if (!d.isBusy()) return d;
        return null;
    }

    public void scheduleEvent(Event e) { eventQueue.add(e); }

    public double generateInterarrivalTime() {
        // exponential interarrival with rate = arrivalRate (per minute)
        return -Math.log(1 - rand.nextDouble()) / arrivalRate;
    }

    public double generateServiceTime() {
        // exponential service time with rate = serviceRate (per minute)
        return -Math.log(1 - rand.nextDouble()) / serviceRate;
    }

    /**
     * Run a batch.
     */
    public static List<RunResult> runBatch(List<Config> configs) throws IOException {
        List<RunResult> results = new ArrayList<>();
        for (Config c : configs) {
            SimulationEngine engine = new SimulationEngine(c);
            RunResult r = engine.run();
            results.add(r);
        }
        return results;
    }

    /**
     * Run a single simulation and return RunResult.
     */
    public RunResult run() {
        // reset state if necessary
        currentTime = 0;
        eventQueue.clear();
        queue = new EDQueue(); // fresh queue
        stats.reset();

        // schedule first arrival at time 0
        scheduleEvent(new ArrivalEvent(0.0));

        // event loop
        while (!eventQueue.isEmpty()) {
            Event e = eventQueue.poll();
            if (e == null) break;
            if (e.getTime() > endTime) break;

            currentTime = e.getTime();
            e.execute(this);

            // After handling event, try to start service while free doc and non-empty queue
            Doctor free = getFreeDoctor();
            while (free != null && !queue.isEmpty()) {
                Patient next = queue.poll();
                if (next == null) break;

                // mark doctor busy with timestamp
                free.setBusy(true, currentTime);

                next.setAssignedDoctor(free);
                next.setServiceStart(currentTime);

                stats.recordServiceStart(next, currentTime);

                double end = currentTime + generateServiceTime();
                scheduleEvent(new ServiceEndEvent(end, next));

                // look for another free doctor (multiple doctors possible)
                free = getFreeDoctor();
            }
        }

        // Gather doctor busy accumulations into stats
        for (Doctor d : doctors) {
            stats.addDoctorBusy(d.getId(), d.getBusyAccum());
        }

        // finalize stats and build RunResult
        stats.finalizeStats(); // prints summary if desired
        Map<String,String> summary = stats.asSummaryMap();

        RunResult rr = new RunResult(runId, summary, null, null);

        stats.finalizeStats();

// === WRITE CSV OUTPUTS ===
        try {
            CSVExporter exporter = new CSVExporter(runId);
            exporter.writeSummary("summary.csv", stats.asSummaryMap());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new RunResult(runId, stats.asSummaryMap(), null, null);
    }

    // Optional minimal exporter if needed (not used by default)
    private void exportSummaryCsv(RunResult r, String filename) throws IOException {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("avg_wait,throughput\n");
            String avg = r.summary != null ? r.summary.getOrDefault("avg_wait", "NA") : "NA";
            String thr = r.summary != null ? r.summary.getOrDefault("throughput", "NA") : "NA";
            fw.write(avg + "," + thr + "\n");
        }
    }
}
