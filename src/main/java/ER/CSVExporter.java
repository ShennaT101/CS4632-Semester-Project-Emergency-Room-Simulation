package ER;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * CSVExporter is a utility class responsible for exporting simulation results
 * to CSV (Comma-Separated Values) files.
 *
 * <p>This class supports:
 * <ul>
 *   <li>Writing time-series data with optional headers</li>
 *   <li>Writing summary statistics as key–value pairs</li>
 *   <li>Automatic creation of output directories</li>
 * </ul>
 *
 * <p>CSV output enables post-processing, visualization, and analysis
 * using external tools such as Excel, Python, or R.
 */
public class CSVExporter {

    /** Base directory where CSV output files are written */
    private final Path baseDir;

    /**
     * Constructs a CSVExporter that writes files to the specified directory.
     *
     * <p>If the directory does not exist, it is automatically created.
     *
     * @param baseDir the base output directory path
     * @throws IOException if the directory cannot be created
     */
    public CSVExporter(String baseDir) throws IOException {
        this.baseDir = Paths.get(baseDir);

        // Ensure output directory exists
        Files.createDirectories(this.baseDir);
    }

    /**
     * Writes a CSV file containing tabular data.
     *
     * <p>Each row in the list corresponds to a line in the CSV file.
     * An optional header row may be written at the top of the file.
     *
     * @param filename name of the CSV file to create
     * @param rows list of string arrays representing data rows
     * @param header optional column header row (may be null)
     * @throws IOException if an error occurs while writing the file
     */
    public void writeCSV(String filename, List<String[]> rows, String[] header)
            throws IOException {

        Path out = baseDir.resolve(filename);

        try (BufferedWriter w = Files.newBufferedWriter(out)) {

            // Write header row if provided
            if (header != null) {
                w.write(String.join(",", header));
                w.write("\n");
            }

            // Write each data row
            for (String[] r : rows) {
                w.write(String.join(",", r));
                w.write("\n");
            }
        }
    }

    /**
     * Writes a summary CSV file containing key–value pairs.
     *
     * <p>This format is typically used for aggregate statistics such as
     * average wait time, utilization, and total arrivals.
     *
     * @param filename name of the summary CSV file
     * @param map map of statistic names to values
     * @throws IOException if an error occurs while writing the file
     */
    public void writeSummary(String filename, Map<String, String> map)
            throws IOException {

        Path out = baseDir.resolve(filename);

        try (BufferedWriter w = Files.newBufferedWriter(out)) {

            // Write each summary statistic as a separate row
            for (Map.Entry<String, String> e : map.entrySet()) {
                w.write(e.getKey() + "," + e.getValue());
                w.write("\n");
            }
        }
    }
}
