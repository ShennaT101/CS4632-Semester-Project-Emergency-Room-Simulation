package ER;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * EDQueue represents the waiting queue for patients in the Emergency Room.
 *
 * <p>The queue prioritizes patients based on:
 * <ol>
 *   <li>Severity level (lower value = higher priority)</li>
 *   <li>Arrival order (FIFO among patients with equal severity)</li>
 * </ol>
 *
 * <p>This implementation ensures deterministic ordering using a sequence
 * number and is thread-safe to support safe event execution.
 */
public class EDQueue {

    /**
     * Internal queue entry wrapper used to preserve FIFO ordering
     * among patients with equal severity.
     */
    private static class QEntry {

        /** Patient in the queue */
        final Patient p;

        /** Sequence number assigned at arrival */
        final long seq;

        QEntry(Patient p, long seq) {
            this.p = p;
            this.seq = seq;
        }
    }

    /** Sequence counter used to enforce FIFO ordering */
    private final AtomicLong seq = new AtomicLong(0);

    /**
     * Priority queue ordering patients by severity first, then arrival order.
     */
    private final PriorityQueue<QEntry> pq = new PriorityQueue<>(
            Comparator.<QEntry>comparingInt(e -> e.p.getSeverity())
                    .thenComparingLong(e -> e.seq)
    );

    /**
     * Adds a patient to the waiting queue.
     *
     * <p>Patients with higher severity are prioritized, while patients
     * with equal severity are served in FIFO order.
     *
     * @param p patient to add to the queue
     */
    public synchronized void add(Patient p) {
        pq.add(new QEntry(p, seq.getAndIncrement()));
    }

    /**
     * Removes and returns the next patient from the queue.
     *
     * @return the next patient, or {@code null} if the queue is empty
     */
    public synchronized Patient poll() {
        QEntry e = pq.poll();
        return e == null ? null : e.p;
    }

    /**
     * Indicates whether the queue is empty.
     *
     * @return {@code true} if no patients are waiting
     */
    public synchronized boolean isEmpty() {
        return pq.isEmpty();
    }

    /**
     * Returns the current number of patients in the queue.
     *
     * @return queue size
     */
    public synchronized int size() {
        return pq.size();
    }
}
