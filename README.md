# Emergency Room Simulation
CS 4632 – Modeling & Simulation  
Milestone 1 – Project Foundation  
Author: Shenna Tawiah
Repository: https://github.com/ShennaT101/CS4632-Semester-Project-Emergency-Room-Simulation

---

## 📘 Project Overview
This project simulates patient flow through a hospital Emergency Department (ED).  
The goal is to analyze how arrival rates, triage priorities, and staffing levels affect:

- Patient waiting time  
- Length of stay  
- Resource utilization  
- Queue buildup by acuity  

This simulation uses a **Discrete-Event Simulation (DES)** model implemented in Java.

---

## 🏥 System Model
### **Entities**
- **Patient** – arrival time, acuity, service time  
- **Triage Nurse** – assigns acuity based on a probability distribution  
- **Doctor** – provides treatment (server)  
- **EDQueue** – priority queue ordered by acuity + FIFO  
- **SimulationEngine** – event loop, calendar, stats

### **Events**
- **ArrivalEvent** – patient arrives, triage performed  
- **ServiceEndEvent** – doctor finishes treating a patient  

### **Core Logic**
- Poisson arrivals  
- Exponential service times  
- Priority scheduling by acuity  
- Multiple servers (doctors)  

---

## 🧮 Mathematical Foundations
- **Arrival process:** Poisson distribution  
- **Service times:** exponential  
- **Queue model:** M/M/c with priority classes  
- **Triage:** categorical probability distribution  

Citations for these models are included in the LaTeX Milestone 1 document.

---

## 🚀 How to Run
### **Compile**
```bash
javac *.java
