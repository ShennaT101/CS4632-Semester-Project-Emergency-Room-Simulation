# CS 4632 – Emergency Room Simulation  
**Modeling and Simulation Final Project**

**Author:** [Shenna Tawiah]  
**Course:** CS 4632 – Modeling and Simulation  
**Institution:** Kennesaw State University  
**Semester:** Fall 2025  

---

## 📌 Project Overview

This project implements a **discrete-event simulation of a hospital emergency room (ER)**.  
The simulation models patient arrivals, waiting queues, doctor availability, and service times to analyze system performance under varying conditions.

The primary goals of this project are to:
- Study how **arrival rates**, **staffing levels**, and **service times** impact patient wait times
- Analyze congestion and resource utilization
- Perform sensitivity analysis, scenario testing, and validation
- Demonstrate complete simulation implementation and analysis across milestones

This is a **hypothetical ER model** and does not use real hospital data.

---

## 🧠 Simulation Features

- Discrete-event simulation engine
- Configurable arrival rates, service times, and number of doctors
- Multiple simulation runs with parameter sweeps
- Automatic CSV data export
- Time-series and summary statistics collection
- Reproducible results via random seeds

---

## 🗂 Project Structure

```text
CS4632-ER-Simulation/
│
├── src/
│   └── ER/
│       ├── Main.java              # Entry point & batch run controller
│       ├── SimulationEngine.java  # Core simulation logic
│       ├── Config.java            # Parameter configuration system
│       ├── Patient.java           # Patient entity
│       ├── Doctor.java            # Doctor resource
│       ├── Event.java             # Simulation events
│       ├── RunResult.java         # Run-level results container
│       └── CSVExporter.java       # CSV output utilities
│
├── runs/
│   ├── run_01/
│   │   ├── summary.csv
│   │   └── timeseries.csv
│   ├── ...
│   └── run_10/
│
├── docs/
│   ├── M3_Report.pdf
│   ├── M4_Report.pdf
│   └── M5_Final_Report.pdf
│
├── screenshots/
│   ├── simulation_running.png
│   ├── csv_output.png
│   └── results_charts.png
│
├── README.md
└── config.properties

---

## ▶️ How to Run the Simulation

### Step 1: Open the Project
- Open **IntelliJ IDEA**
- Select **Open**
- Choose the project root directory

### Step 2: Configure Java
File → Project Structure → Project SDK → Java 21


### Step 3: Run the Simulation
- Open `Main.java`
- Right-click → **Run Main**

---

## 🔧 Changing Simulation Parameters
Simulation parameters are configured directly in **`Main.java`**.

### Example Configuration
```java
configs.add(copy(base, 40, 3, 12, 103, "runs/run_03"));

Parameter Order
(arrivalRatePerHour, numberOfDoctors, serviceMeanMinutes, randomSeed, outputDirectory)

## 🔧 Adjustable Parameters
The following parameters can be modified to explore different Emergency Room scenarios:

- **Arrival rate** (patients per hour)
- **Number of doctors**
- **Mean service time** (minutes)
- **Random seed** (for reproducibility)

---

## 📊 Output Data
Each simulation run produces the following output file:
runs/run_XX/summary.csv


### Metrics Collected
- Total patient arrivals
- Total patient departures
- Average waiting time
- Average service time
- Doctor busy time (utilization)

---

## 📈 Milestone 4 – Analysis & Validation

### Sensitivity Analysis
Three key parameters were analyzed:

- Arrival rate
- Number of doctors
- Service time

Results showed that **arrival rate** had the strongest impact on system congestion and patient waiting times.

### Scenario Testing
The following scenarios were tested:

- Baseline ER load
- High-demand stress scenario
- Low-resource scenario
- Overstaffed scenario

### Validation
Validation methods included:

- Face validation (expected queue behavior)
- Extreme-condition testing
- Logical consistency checks

This simulation represents a **hypothetical Emergency Room**, so validation focuses on qualitative correctness rather than real hospital data.

---

## 🎥 Milestone 5 – Video Demonstration
A narrated video demonstrates:

- Live simulation execution
- Parameter changes
- Output interpretation
- Key findings

📎 **Video Link:** Provided in D2L submission

---

## 🧠 Key Findings
- Increased arrival rates significantly increase patient wait times
- Additional doctors reduce congestion but exhibit diminishing returns
- Service time strongly affects overall system stability

---

## 🚀 Future Work
- Add patient priority levels
- Implement time-series data collection
- Add visualization or GUI
- Compare against real-world ER datasets
