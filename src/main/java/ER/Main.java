package ER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Config base = Config.loadFromFile("config.properties");

        // Example 10-run sweep: vary arrivalRatePerHour, numDoctors, serviceMeanMinutes
        List<Config> configs = new ArrayList<>();
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

        // run batch
        List<RunResult> results = SimulationEngine.runBatch(configs);

        // Print summary table
        System.out.println("\n=== BATCH SUMMARY ===");
        System.out.println("Run,arrival/hr,doctors,service_mean_min,avg_wait_min,wall_seconds");
        for (int i=0;i<results.size();i++) {
            RunResult r = results.get(i);
            Config c = configs.get(i);
            String avgWait = r.summary.getOrDefault("avg_wait", "NA");
            String wall = r.summary.getOrDefault("wall_seconds", "NA");
            System.out.printf("%s,%.1f,%d,%.1f,%s,%s%n", r.id, c.arrivalRatePerHour, c.numDoctors, c.serviceMeanMinutes, avgWait, wall);
        }
    }

    private static Config copy(Config base, double arrivalRate, int docs, double serviceMean, long seed, String outdir) {
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
