# Emergency Room Simulation  
CS 4632 – Modeling & Simulation  
Author: **Shenna Tawiah**  
Repository: **https://github.com/ShennaT101/CS4632-Semester-Project-Emergency-Room-Simulation**

---

## 📘 Project Overview
This project implements a **Discrete-Event Simulation (DES)** of patient flow through a hospital Emergency Department (ED). The simulation analyzes how arrival rates, triage priorities, staffing levels, and bed availability affect:

- Patient waiting time  
- Length of stay (LOS)  
- Resource utilization (doctors, beds)  
- Queue length by acuity level  
- Overall system throughput  

The simulation is written entirely in **Java**, using an event-driven architecture with a priority-based event calendar.

---

## 🏥 System Model

### **Entities**
- **Patient** – attributes: arrival time, service time, ESI acuity level  
- **Doctor** – server that treats patients  
- **Bed** – capacity resource for ED  
- **Triage Nurse** – assigns ESI priority using probability distribution  
- **EventQueue** – priority queue (future event list)  
- **SimulationEngine** – handles events, time advancement, statistics  

---

### **Event Types**
#### **ArrivalEvent**
- Generates patient arrivals (Poisson process)  
- Assigns ESI level  
- Places patient in priority queue  
- Triggers next arrival  

#### **ServiceStartEvent**
- Occurs when doctor + bed become available  
- Removes patient from waiting queue  
- Schedules service completion  

#### **ServiceEndEvent**
- Frees resources  
- Updates statistics  
- Starts next patient if queue is non-empty  

---

## 🧮 Mathematical Foundations

| Component | Model |
|----------|-------|
| **Arrival process** | Poisson distribution |
| **Service time** | Exponential distribution |
| **Queue structure** | M/M/c with 5 priority classes |
| **Triage process** | Categorical probability distribution |
| **Scheduling** | Priority queue (lowest ESI = highest priority) |

These foundational models are fully documented and cited in the Milestone 1 LaTeX deliverable.

---

## 📊 Simulation Outputs
The simulation collects and reports:

### **Per-Acuity Output**
- Mean wait time  
- Mean length of stay  
- Number of patients treated  
- Queue length distribution  

### **System-Level Output**
- Total throughput  
- Doctor utilization  
- Bed utilization  
- Overall average wait time  
- Total number of events processed  

---

## 📐 UML Diagrams  
UML diagrams are located in the `/docs/` folder:

- `classDiagram.png` – Overall class structure  
- `sequenceDiagram.png` – Event flow during simulation  

PlantUML source files are included for reproducibility.

---

## 📁 Project Structure

