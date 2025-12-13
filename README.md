# 🏥 CS 4632 – Emergency Room Simulation (Milestone 3)

## 📌 Project Overview

This project implements a **discrete-event simulation (DES)** of a hospital emergency room (ER)
system. Patients arrive stochastically, wait in a queue if necessary, receive service from
available doctors, and then depart the system.

Milestone 3 completes the full simulation implementation and focuses on **batch execution,
data collection, verification, and documentation**.

---

## 🎯 Milestone 3 Objectives

- Complete simulation logic and entity lifecycle management
- Execute multiple simulation runs with varied parameters
- Collect and export performance metrics automatically
- Perform basic verification testing
- Document execution results and observations

---

## ⚙️ Simulation Features

- Discrete-event simulation using a priority event queue
- Exponential interarrival times for patient arrivals
- Exponential service times
- Multiple doctor resources with busy/idle tracking
- Patient queue management
- Batch execution of multiple simulation scenarios
- CSV export of summary performance metrics

---

## 🧩 Project Structure
CS4632-ER-Simulation/
├── src/
│ └── ER/
│ ├── ArrivalEvent.java
│ ├── ServiceEndEvent.java
│ ├── DepartureEvent.java
│ ├── SimulationEngine.java
│ ├── StatsCollector.java
│ ├── CSVExporter.java
│ ├── Doctor.java
│ ├── Patient.java
│ ├── EDQueue.java
│ ├── Config.java
│ ├── RunResult.java
│ └── Main.java
├── runs/
│ ├── run_01/
│ ├── run_02/
│ ├── run_03/
│ ├── run_04/
│ ├── run_05/
│ ├── run_06/
│ ├── run_07/
│ ├── run_08/
│ ├── run_09/
│ └── run_10/
├── README.md
└── .gitignore


---

## 🧪 Parameters

Each simulation run may vary the following parameters:

- Arrival rate (patients per hour)
- Mean service time (minutes)
- Number of doctors
- Simulation duration (hours)
- Random seed (for reproducibility)

Parameters are configured programmatically and passed to the simulation engine
for each batch run.

---

## 📊 Data Collection

Each simulation run automatically collects:

- Total number of arrivals
- Total number of departures (throughput)
- Average patient waiting time (minutes)
- Average service time (minutes)
- Per-doctor busy time (minutes)

All output data is exported in **CSV format** to the `runs/` directory.

---

## ▶️ How to Run

### Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA
2. Ensure Java 21 (or compatible) is configured
3. Run `Main.java`
4. Observe summary output in the console
5. CSV files are generated under the `runs/` directory

---

## ✅ Verification

Basic verification included:

- Ensuring arrivals and departures are conserved
- Confirming doctors are not assigned to multiple patients simultaneously
- Observing expected performance trends under increasing load

All simulation runs completed successfully.

---

## 📈 Milestone 3 Results Summary

Ten simulation runs were executed with varied arrival rates, service times,
and the number of doctors. Results demonstrate expected queueing behavior:

- Average waiting time increases under higher arrival rates
- Adding doctors improves throughput and reduces congestion
- Faster service rates significantly reduce waiting times

These results provide a strong foundation for sensitivity analysis in Milestone 4.

---

## 🔗 Repository

GitHub Repository:  
https://github.com/ShennaT101/CS4632-Semester-Project-Emergency-Room-Simulation.git

---

## 🚀 Next Steps

Milestone 4 will focus on:

- Sensitivity analysis
- Performance visualization
- Model validation and deeper analysis

