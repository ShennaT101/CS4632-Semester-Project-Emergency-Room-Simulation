package ER;

import java.util.PriorityQueue;

public class EDQueue {

    private PriorityQueue<Patient> pq = new PriorityQueue<>();

    public void add(Patient p) {
        pq.add(p);
    }

    public Patient poll() {
        return pq.poll();
    }

    public boolean isEmpty() { return pq.isEmpty(); }

    public int size() { return pq.size(); }
}
