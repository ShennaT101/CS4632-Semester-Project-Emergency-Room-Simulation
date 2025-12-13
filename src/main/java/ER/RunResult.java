package ER;

import java.util.Map;

/**
 * RunResult encapsulates the output of a single simulation run.
 *
 * <p>This class serves as a lightweight container for:
 * <ul>
 *   <li>Run identifier</li>
 *   <li>Summary performance metrics</li>
 *   <li>Paths to generated CSV output files</li>
 * </ul>
 *
 * <p>Instances of this class are returned by batch executions and
 * used for reporting and comparative analysis.
 */
public class RunResult {

    /** Unique identifier for the simulation run */
    public final String id;

    /** Summary statistics generated at the end of the run */
    public final Map<String, String> summary;

    /** Path to the time-series CSV output file */
    public final String timeseries;

    /** Path to the event log output file */
    public final String events;

    /**
     * Constructs a new RunResult.
     *
     * @param id         unique run identifier
     * @param summary    map of summary statistics
     * @param timeseries path to time-series output
     * @param events     path to event log output
     */
    public RunResult(
            String id,
            Map<String, String> summary,
            String timeseries,
            String events
    ) {
        this.id = id;
        this.summary = summary;
        this.timeseries = timeseries;
        this.events = events;
    }
}
