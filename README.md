# CS 4632 – Emergency Room Simulation

**Modeling and Simulation Final Project**

**Author:** Shenna Tawiah

**Course:** CS 4632 – Modeling and Simulation

**Institution:** Kennesaw State University

**Semester:** Fall 2025

---

## 📌 Project Overview

This project implements a **discrete-event simulation** of a hospital **Emergency Room (ER)**. The simulation models patient arrivals, waiting queues, doctor availability, and service times in order to evaluate system performance under varying conditions.

The model is **hypothetical** and does not use real hospital data. Its purpose is to demonstrate **simulation design, experimentation, validation, and analysis techniques** as required for the CS 4632 course.

---

## 🎯 Project Objectives

The primary goals of this project are to:

* Analyze how **arrival rates**, **staffing levels**, and **service times** impact patient waiting times
* Study **congestion effects** and **doctor utilization**
* Perform **sensitivity analysis** and **scenario testing**
* Validate model behavior using **qualitative** and **extreme-condition tests**
* Demonstrate a **complete simulation workflow** across multiple milestones

---

## 🧠 Simulation Features

* Discrete-event simulation engine
* Configurable arrival rates, service times, and number of doctors
* Multiple simulation runs with parameter sweeps
* Automatic CSV data export
* Time-series and summary statistics collection
* Reproducible results using random seeds
* Modular and well-documented Java codebase

---

## 🧩 Modeling Assumptions

The simulation is based on the following assumptions:

* Patients are homogeneous (no priority levels)
* Patients are served on a **First-In, First-Out (FIFO)** basis
* Each doctor can serve **one patient at a time**
* Service times follow a **probabilistic distribution**
* Patients do **not abandon** the queue
* Doctors are continuously available during the simulation

These assumptions simplify the system while preserving key ER dynamics.

---

## 🗂 Project Structure

```text
CS4632-ER-Simulation/
│
├── src/
│   └── ER/
│       ├── Main.java               # Entry point & batch run controller
│       ├── SimulationEngine.java   # Core discrete-event simulation logic
│       ├── Config.java             # Parameter configuration system
│       ├── Patient.java            # Patient entity definition
│       ├── Doctor.java             # Doctor resource model
│       ├── Event.java              # Simulation event abstraction
│       ├── RunResult.java           # Run-level results container
│       └── CSVExporter.java         # CSV output utilities
│
├── runs/                            # Simulation output directories
│   ├── run_01/
│   │   ├── summary.csv
│   │   └── timeseries.csv
│   ├── ...
│   └── run_10/
│
├── README.md                        # Project documentation (this file)
└── config.properties                # Optional configuration file
```

---

## ▶️ How to Run the Simulation

### Step 1: Open the Project

1. Open **IntelliJ IDEA**
2. Select **Open**
3. Choose the project root directory (`CS4632-ER-Simulation`)

### Step 2: Configure Java SDK

1. Go to **File → Project Structure**
2. Set **Project SDK** to **Java 21**

### Step 3: Run the Simulation

1. Open `Main.java`
2. Right-click → **Run Main**

The simulation will execute multiple runs and generate output files in the `runs/` directory.

---

## 🔧 Configuring Simulation Parameters

Simulation parameters are configured directly in `Main.java`.

### Example Configuration

```java
configs.add(copy(base, 40, 3, 12, 103, "runs/run_03"));
```

### Parameter Order

```text
(arrivalRatePerHour, numberOfDoctors, serviceMeanMinutes, randomSeed, outputDirectory)
```

### Adjustable Parameters

* **Arrival rate** (patients per hour)
* **Number of doctors**
* **Mean service time** (minutes)
* **Random seed** (for reproducibility)

These parameters allow exploration of different ER workload and staffing scenarios.

---

## 📊 Output Data

Each simulation run generates two CSV files in its corresponding run directory:

```text
runs/run_XX/
├── summary.csv
```

### Metrics Collected

* Total patient arrivals
* Total patient departures
* Average waiting time
* Average service time
* Doctor busy time (utilization)

These outputs support scenario comparison, visualization, and statistical analysis.

---

## 📈 Analysis & Validation (Milestone 4 Summary)

### Sensitivity Analysis

Three key parameters were analyzed:

* Arrival rate
* Number of doctors
* Service time

**Arrival rate** had the strongest impact on congestion and patient waiting times.

### Scenario Testing

The following scenarios were evaluated:

* Baseline ER load
* High-demand stress scenario
* Low-resource scenario
* Overstaffed scenario

### Validation Methods

Because the model is hypothetical, validation focused on **behavioral realism**:

* Face validation (expected queue behavior)
* Extreme-condition testing
* Logical consistency checks

---

## 🎥 Milestone 5 – Video Demonstration

A narrated demonstration video shows:

* Live simulation execution
* Parameter modifications
* Output interpretation
* Key analytical findings

📎 **Video Link:** Provided in D2L submission

---

## 🧠 Key Findings

* Higher arrival rates significantly increase patient wait times
* Adding doctors reduces congestion but exhibits diminishing returns
* Service time strongly affects system stability and throughput

---

## 🚀 Future Work

Potential extensions to this project include:

* Adding patient priority levels
* Implementing time-varying arrival rates
* Adding real-time visualization or a GUI
* Incorporating patient abandonment behavior
* Comparing results against real ER datasets

---

## 📌 Disclaimer

This simulation represents a **hypothetical emergency room system** and is intended for **academic and educational purposes only**. It should not be used for real-world medical or operational decision-making.

---

## 📎 Repository

The full source code, documentation are available in this public GitHub repository:

**[https://github.com/ShennaT101/CS4632-Semester-Project-Emergency-Room-Simulation.git]**
