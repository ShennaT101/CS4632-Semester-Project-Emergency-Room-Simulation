package ER;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

public class EDQueue {
    private static class QEntry {
        final Patient p;
        final long seq;
        QEntry(Patient p, long seq) { this.p = p; this.seq = seq; }
    }

    private final AtomicLong seq = new AtomicLong(0);
    private final PriorityQueue<QEntry> pq = new PriorityQueue<>(
            Comparator.<QEntry>comparingInt(e -> e.p.getSeverity())
                    .thenComparingLong(e -> e.seq)
    );

    public synchronized void add(Patient p) { pq.add(new QEntry(p, seq.getAndIncrement())); }

    public synchronized Patient poll() {
        QEntry e = pq.poll();
        return e == null ? null : e.p;
    }

    public synchronized boolean isEmpty() { return pq.isEmpty(); }

    public synchronized int size() { return pq.size(); }
}
