package ER;

/**
 * Event is the abstract base class for all discrete events
 * in the Emergency Room simulation.
 *
 * <p>Each event:
 * <ul>
 *   <li>Occurs at a specific simulation time</li>
 *   <li>Is processed in chronological order</li>
 *   <li>Executes logic that updates system state</li>
 * </ul>
 *
 * <p>Concrete subclasses must implement the {@link #execute(SimulationEngine)}
 * method to define event-specific behavior.
 */
public abstract class Event implements Comparable<Event> {

    /** Simulation time at which the event occurs */
    protected double time;

    /**
     * Constructs an event scheduled at the specified simulation time.
     *
     * @param time simulation time of the event
     */
    public Event(double time) {
        this.time = time;
    }

    /**
     * Returns the simulation time of this event.
     *
     * @return event time
     */
    public double getTime() {
        return time;
    }

    /**
     * Compares events based on their scheduled simulation time.
     *
     * <p>This ordering ensures that events are processed in chronological order
     * by the simulation engine.
     *
     * @param other another event to compare against
     * @return negative if this event occurs earlier, positive if later
     */
    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }

    /**
     * Executes the event logic.
     *
     * <p>This method is called by the simulation engine when the event
     * is dequeued from the event calendar.
     *
     * @param engine the simulation engine controlling execution
     */
    public abstract void execute(SimulationEngine engine);
}
