package ER;

import java.util.Map;

public class RunResult {
    public final String id;
    public final Map<String,String> summary;
    public final String timeseries;
    public final String events;

    public RunResult(String id, Map<String,String> summary, String timeseries, String events) {
        this.id = id; this.summary = summary; this.timeseries = timeseries; this.events = events;
    }
}
