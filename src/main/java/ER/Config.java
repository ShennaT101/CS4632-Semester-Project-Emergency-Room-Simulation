package ER;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Config encapsulates all configurable parameters for the Emergency Room
 * discrete-event simulation.
 *
 * <p>This class supports:
 * <ul>
 *   <li>Default simulation parameter values</li>
 *   <li>Optional loading of parameters from an external configuration file</li>
 *   <li>Centralized management of simulation settings</li>
 * </ul>
 *
 * <p>If a configuration file is not found or cannot be read, the simulation
 * safely falls back to default values.
 */
public class Config {

    /** Patient arrival rate (patients per hour) */
    public double arrivalRatePerHour = 20.0;

    /** Mean service time per patient (minutes) */
    public double serviceMeanMinutes = 15.0;

    /** Number of doctors available in the ER */
    public int numDoctors = 3;

    /** Total simulation duration (hours) */
    public double simHours = 8.0;

    /** Random seed for reproducibility */
    public long randomSeed = 42L;

    /** Output directory for simulation results */
    public String outputDir = "runs";

    /** Time interval for recording time-series statistics (minutes) */
    public double timeseriesIntervalMinutes = 5.0;

    /**
     * Loads simulation parameters from a properties file.
     *
     * <p>The configuration file may specify any subset of supported parameters.
     * Missing values are automatically filled using defaults.
     *
     * <p>Supported property keys:
     * <ul>
     *   <li>{@code arrival.rate.per.hour}</li>
     *   <li>{@code service.mean.minutes}</li>
     *   <li>{@code num.doctors}</li>
     *   <li>{@code sim.hours}</li>
     *   <li>{@code random.seed}</li>
     *   <li>{@code output.dir}</li>
     *   <li>{@code timeseries.interval.minutes}</li>
     * </ul>
     *
     * @param path path to the {@code config.properties} file
     * @return a {@link Config} object populated with loaded or default values
     */
    public static Config loadFromFile(String path) {

        // Create a configuration instance with default values
        Config c = new Config();

        try {
            File f = new File(path);

            // If config file does not exist, return defaults
            if (!f.exists()) {
                return c;
            }

            // Load properties from file
            Properties p = new Properties();
            p.load(new FileInputStream(f));

            // Parse and assign properties, falling back to defaults if missing
            c.arrivalRatePerHour = Double.parseDouble(
                    p.getProperty(
                            "arrival.rate.per.hour",
                            Double.toString(c.arrivalRatePerHour)
                    )
            );

            c.serviceMeanMinutes = Double.parseDouble(
                    p.getProperty(
                            "service.mean.minutes",
                            Double.toString(c.serviceMeanMinutes)
                    )
            );

            c.numDoctors = Integer.parseInt(
                    p.getProperty(
                            "num.doctors",
                            Integer.toString(c.numDoctors)
                    )
            );

            c.simHours = Double.parseDouble(
                    p.getProperty(
                            "sim.hours",
                            Double.toString(c.simHours)
                    )
            );

            c.randomSeed = Long.parseLong(
                    p.getProperty(
                            "random.seed",
                            Long.toString(c.randomSeed)
                    )
            );

            c.outputDir = p.getProperty("output.dir", c.outputDir);

            c.timeseriesIntervalMinutes = Double.parseDouble(
                    p.getProperty(
                            "timeseries.interval.minutes",
                            Double.toString(c.timeseriesIntervalMinutes)
                    )
            );

        } catch (Exception ex) {
            // Fail gracefully and continue using default values
            System.err.println(
                    "Warning: failed to read config.properties — using defaults. "
                            + ex.getMessage()
            );
        }

        return c;
    }
}
