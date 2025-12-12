# 🚑 Emergency Room Simulation  
### CS4632 – Milestone 2

This project implements a **Discrete Event Simulation (DES)** of an Emergency Room (ER).  
Patients arrive randomly, wait in queues, get assigned to available doctors, receive care, and leave the system.  
The goal is to model system flow, analyze performance, and evaluate queueing behavior.

---

## 📌 Project Status (Milestone 2)

### ✔ Implemented So Far
- Core simulation engine  
- Event-driven scheduling (priority queue)  
- Patient and Doctor entity classes  
- Arrival event processing  
- Service event processing  
- Waiting room queue (FIFO)  
- Time progression based on event clock  
- Basic statistics tracking:  
  - patient wait times  
  - queue lengths  
  - doctor utilization  
- Basic console output showing simulation progress

### 🚧 Still To Come
- Additional event types (DepartureEvent, TriageEvent, etc.)  
- More detailed patient behavior (severity levels, service variance)  
- Configurable parameters via external file or CLI  
- Extended statistical reporting  
- Visualization tools (graphs, metrics)  

### 🔄 Changes From Proposal
- Event classes adjusted to simplify processing  
- StatsCollector added earlier for easier debugging  
- Some UML elements streamlined for implementation clarity

---

## 🔧 Installation & Setup

### Requirements
- **Java 21**  
- **Maven** (auto-managed by IntelliJ)

### How to Run (IDE)
1. Open project in IntelliJ  
2. Navigate to:
   ```
   src/main/java/ER/Main.java
   ```
3. Right-click → **Run 'Main'**

### How to Run (Terminal)
```
mvn clean compile
mvn exec:java -Dexec.mainClass="ER.Main"
```

---

## ▶ Usage

When the simulation runs, it will:

- Generate patients at random arrival intervals  
- Place waiting patients into a queue  
- Assign patients to available doctors  
- Create and process service completion events  
- Print core statistics at the end  

**Current output includes:**
- Timestamped event log  
- Queue size changes  
- Doctor assignment messages  
- Summary statistics after simulation ends  

---

## 🏗 Architecture Overview

### Core Components
- **SimulationEngine**  
  Controls event loop, system clock, and simulation state  

- **Event** (Base class)  
  - `ArrivalEvent`  
  - `ServiceEvent`  
  - (More coming in M3)

- **Entities**  
  - `Patient`  
  - `Doctor`

- **Queue Model**  
  FIFO queue stored in an internal data structure  

- **StatsCollector**  
  Tracks:
  - average wait time  
  - service time  
  - queue lengths  
  - doctor utilization  

### UML Mapping
The implemented components map directly to the UML diagrams in the proposal:
- Event classes → event hierarchy  
- SimulationEngine → sequence diagram event loop  
- Entities (Patient, Doctor) → UML class diagram  
- Queue/Stats modules → system design components  

---

## 📁 Project Structure

```
CS4632-ER/
│
├── src/
│   └── main/java/ER/
│       ├── ArrivalEvent.java
│       ├── ServiceEvent.java
│       ├── Patient.java
│       ├── Doctor.java
│       ├── Event.java
│       ├── SimulationEngine.java
│       ├── StatsCollector.java
│       └── Main.java
│
├── classdiagram.png
├── sequencediagram.png
├── pom.xml
└── README.md
```

---

## 🗂 Version Control Notes

This repository demonstrates:
- Multiple incremental commits  
- Clear commit messages  
- Merge conflict resolution (`.gitignore`)  
- Connected remote branch (`origin/main`)  
- Organized folder structure  

---
