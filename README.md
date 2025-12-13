# CS 4632 – Emergency Room Simulation  
## Milestone 4: Analysis & Validation

### Course
CS 4632 – Modeling and Simulation  
Department of Computer Science  
Kennesaw State University  

---

## Project Overview
This project is a **discrete-event simulation (DES)** of a **hypothetical Emergency Room (ER)**.
The simulation models patient arrivals, queueing behavior, doctor service, and throughput over time.

Milestone 4 focuses on **analysis, scenario testing, and validation** using data collected in
Milestone 3.

No real hospital data is used; all parameters represent a **conceptual ER system**.

---

## Simulation Description
The ER simulation includes:
- Poisson patient arrivals
- Exponentially distributed service times
- Configurable number of doctors
- FIFO patient queue
- Event-based execution (arrival, service start, service end)

Each run simulates a fixed number of hours and outputs summary statistics.

---

## Parameters Analyzed (Sensitivity Analysis)

| Parameter | Description |
|----------|-------------|
| Arrival Rate | Patients per hour |
| Number of Doctors | Available service resources |
| Service Time | Mean service duration (minutes) |

Each parameter was varied independently to measure its impact on performance metrics.

---

## Scenario Testing
Three main scenarios were evaluated:

### 1. Baseline Scenario
- Moderate arrival rate
- Adequate staffing
- Average service times

### 2. High-Load Scenario
- High arrival rate
- Limited number of doctors
- Increased congestion and waiting time

### 3. Optimized Staffing Scenario
- High arrival rate
- Increased number of doctors
- Reduced waiting time and higher throughput

---

## Metrics Collected
The following metrics were automatically collected and exported as CSV files:

- Total patient arrivals
- Total patient departures (throughput)
- Average patient waiting time
- Average service time
- Doctor utilization (busy minutes)

---

## Statistical Analysis
For key metrics (e.g., average wait time), the following were analyzed across runs:

- Mean
- Minimum
- Maximum
- Standard deviation
- Qualitative confidence assessment

Observed variability reflects the stochastic nature of the system.

---

## Validation Approach

### Face Validation
- Higher arrival rates increased waiting time
- Increasing doctors reduced congestion
- System overload occurred when arrival rate exceeded service capacity

### Extreme-Condition Testing
- Very low arrival rates produced minimal queues
- Single-doctor scenarios showed near-continuous utilization
- High arrival rates caused queue growth

These behaviors align with expected queueing theory results.

---

## Limitations
- Hypothetical ER (no real-world calibration)
- No patient prioritization (triage)
- Exponential service-time assumption
- No doctor shift changes or fatigue modeling

---

## Project Structure
src/ER/                 # Java source code
  ├── Main.java
  ├── SimulationEngine.java
  ├── Event.java
  ├── ArrivalEvent.java
  ├── ServiceEndEvent.java
  ├── Doctor.java
  ├── Patient.java
  ├── StatsCollector.java
  ├── CSVExporter.java
runs/                   # Output data
  ├── run_01/
  │   └── summary.csv
  ├── ...
config.properties       # Default configuration
README.md

---

## How to Run
1. Open the project in **IntelliJ IDEA**
2. Ensure **JDK 21** is configured
3. Run `Main.java`
4. CSV output files will appear in the `runs/` directory

---

## Milestone Status
- ✅ Milestone 3: Complete implementation and data collection
- ✅ Milestone 4: Analysis, scenario testing, and validation complete
- ⏭ Milestone 5: Final report and presentation

