package ER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main is the entry point for the Emergency Room simulation.
 *
 * <p>This class:
 * <ul>
 *   <li>Loads base configuration settings</li>
 *   <li>Defines a batch of simulation scenarios</li>
 *   <li>Executes multiple simulation runs</li>
 *   <li>Prints a summary of results to the console</li>
 * </ul>
 *
 * <p>Batch execution enables sensitivity analysis and scenario comparison
 * across varying arrival rates, staffing levels, and service times.
 */
public class Main {

    /**
     * Program entry point.
     *
     * <p>Runs a predefined batch of simulation configurations and outputs
     * a summary table of key performance metrics.
     *
     * @param args command-line arguments (not used)
     * @throws IOException if output directories cannot be created
     */
    public static void main(String[] args) throws IOException {

        // Load base configuration from optional properties file
        Config base = Config.loadFromFile("config.properties");

        // Define batch of simulation configurations
        List<Config> configs = new ArrayList<>();

        // Example 10-run sweep varying arrival rate, staffing, and service time
        configs.add(copy(base, 20, 2, 15, 101, "runs/run_01"));
        configs.add(copy(base, 30, 2, 15, 102, "runs/run_02"));
        configs.add(copy(base, 40, 3, 12, 103, "runs/run_03"));
        configs.add(copy(base, 25, 3, 20, 104, "runs/run_04"));
        configs.add(copy(base, 50, 4, 10, 105, "runs/run_05"));
        configs.add(copy(base, 15, 2, 18, 106, "runs/run_06"));
        configs.add(copy(base, 35, 3, 14, 107, "runs/run_07"));
        configs.add(copy(base, 60, 5, 8, 108, "runs/run_08"));
        configs.add(copy(base, 10, 1, 30, 109, "runs/run_09"));
        configs.add(copy(base, 80, 6, 7, 110, "runs/run_10"));

        // Execute batch simulation
        List<RunResult> results = SimulationEngine.runBatch(configs);

        // Print summary table to console
        System.out.println("\n=== BATCH SUMMARY ===");
        System.out.println("Run,arrival/hr,doctors,service_mean_min,avg_wait_min,wall_seconds");

        for (int i = 0; i < results.size(); i++) {
            RunResult r = results.get(i);
            Config c = configs.get(i);

            String avgWait = r.summary.getOrDefault("avg_wait", "NA");
            String wall = r.summary.getOrDefault("wall_seconds", "NA");

            System.out.printf(
                    "%s,%.1f,%d,%.1f,%s,%s%n",
                    r.id,
                    c.arrivalRatePerHour,
                    c.numDoctors,
                    c.serviceMeanMinutes,
                    avgWait,
                    wall
            );
        }
    }

    /**
     * Creates a copy of a base configuration with selected parameters overridden.
     *
     * <p>This method simplifies batch configuration setup by reusing shared
     * parameters while varying key experimental factors.
     *
     * @param base        base configuration
     * @param arrivalRate patient arrival rate (per hour)
     * @param docs        number of doctors
     * @param serviceMean mean service time (minutes)
     * @param seed        random seed
     * @param outdir      output directory for the run
     * @return configured {@link Config} instance
     */
    private static Config copy(
            Config base,
            double arrivalRate,
            int docs,
            double serviceMean,
            long seed,
            String outdir
    ) {
        Config c = new Config();
        c.arrivalRatePerHour = arrivalRate;
        c.numDoctors = docs;
        c.serviceMeanMinutes = serviceMean;
        c.simHours = base.simHours;
        c.randomSeed = seed;
        c.outputDir = outdir;
        c.timeseriesIntervalMinutes = base.timeseriesIntervalMinutes;
        return c;
    }
}
