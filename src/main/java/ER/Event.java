package ER;

public abstract class Event implements Comparable<Event> {
    protected double time;

    public Event(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }

    public abstract void execute(SimulationEngine engine);
}
