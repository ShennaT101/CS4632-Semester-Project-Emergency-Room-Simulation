package ER;

import java.util.PriorityQueue;

public class EDQueue {
    private PriorityQueue<Patient> queue = new PriorityQueue<>(
            (a, b) -> Integer.compare(b.getSeverity(), a.getSeverity())  // highest severity first
    );

    public void add(Patient p) {
        queue.add(p);
    }

    public Patient poll() {
        return queue.poll();
    }

    public boolean isEmpty() { return queue.isEmpty(); }

    public int size() { return queue.size(); }
}
