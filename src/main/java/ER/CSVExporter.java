package ER;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class CSVExporter {
    private final Path baseDir;

    public CSVExporter(String baseDir) throws IOException {
        this.baseDir = Paths.get(baseDir);
        Files.createDirectories(this.baseDir);
    }

    public void writeCSV(String filename, List<String[]> rows, String[] header) throws IOException {
        Path out = baseDir.resolve(filename);
        try (BufferedWriter w = Files.newBufferedWriter(out)) {
            if (header != null) w.write(String.join(",", header) + "\n");
            for (String[] r : rows) {
                w.write(String.join(",", r));
                w.write("\n");
            }
        }
    }

    public void writeSummary(String filename, Map<String,String> map) throws IOException {
        Path out = baseDir.resolve(filename);
        try (BufferedWriter w = Files.newBufferedWriter(out)) {
            for (Map.Entry<String,String> e : map.entrySet()) {
                w.write(e.getKey() + "," + e.getValue());
                w.write("\n");
            }
        }
    }
}
