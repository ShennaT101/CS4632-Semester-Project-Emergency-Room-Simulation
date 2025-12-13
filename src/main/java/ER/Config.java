package ER;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    public double arrivalRatePerHour = 20.0;
    public double serviceMeanMinutes = 15.0;
    public int numDoctors = 3;
    public double simHours = 8.0;
    public long randomSeed = 42L;
    public String outputDir = "runs";
    public double timeseriesIntervalMinutes = 5.0;

    public static Config loadFromFile(String path) {
        Config c = new Config();
        try {
            File f = new File(path);
            if (!f.exists()) return c;
            Properties p = new Properties();
            p.load(new FileInputStream(f));
            c.arrivalRatePerHour = Double.parseDouble(p.getProperty("arrival.rate.per.hour", Double.toString(c.arrivalRatePerHour)));
            c.serviceMeanMinutes = Double.parseDouble(p.getProperty("service.mean.minutes", Double.toString(c.serviceMeanMinutes)));
            c.numDoctors = Integer.parseInt(p.getProperty("num.doctors", Integer.toString(c.numDoctors)));
            c.simHours = Double.parseDouble(p.getProperty("sim.hours", Double.toString(c.simHours)));
            c.randomSeed = Long.parseLong(p.getProperty("random.seed", Long.toString(c.randomSeed)));
            c.outputDir = p.getProperty("output.dir", c.outputDir);
            c.timeseriesIntervalMinutes = Double.parseDouble(p.getProperty("timeseries.interval.minutes", Double.toString(c.timeseriesIntervalMinutes)));
        } catch (Exception ex) {
            System.err.println("Warning: failed to read config.properties — using defaults. " + ex.getMessage());
        }
        return c;
    }
}
