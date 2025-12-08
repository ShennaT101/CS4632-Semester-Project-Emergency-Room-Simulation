# Emergency Room Simulation  
CS 4632 – Modeling & Simulation  
Milestone 2 – Code Progress  
Author: Shenna Tawiah  

Repository: https://github.com/ShennaT101/CS4632-Semester-Project-Emergency-Room-Simulation  

---

## 📌 Project Status (Milestone 2)

### ✅ Implemented So Far
- Basic Java project structure created
- Core classes added:
  - Patient, Doctor, TriageNurse
  - Event, ArrivalEvent, ServiceEndEvent
  - EDQueue (priority queue)
  - SimulationEngine (initial event loop)
- `.gitignore` and `pom.xml` configured
- README and documentation structure created
- Initial progress toward Poisson arrivals and exponential service times

### 🔧 Still To Come (Milestone 3)
- Full event scheduling logic
- Poisson arrival generator
- Service time distribution
- Multiple doctors (c-server system)
- Statistics collection and export
- Final UML updates
- Experimentation and results

### 🔄 Changes Since M1
- Switched emphasis to Maven project structure in Java
- Modified class design to fit Java conventions
- Added explicit event subclasses for clean simulation flow

---

## 🛠 Installation Instructions

### **Requirements**
- Java 17+
- Maven 3+
- IntelliJ IDEA (recommended)

### **Setup**
1. Clone the repository:
   ```bash
   git clone https://github.com/ShennaT101/CS4632-Semester-Project-Emergency-Room-Simulation
   ```
2. Open IntelliJ → *Open Project* → select folder  
3. IntelliJ will automatically import the Maven project

### Troubleshooting
- If `pom.xml` fails to load → File → Invalidate Cache & Restart
- If Maven does not sync → Click *Reload Maven Project*

---

## ▶️ Running the Simulation

Compile and run:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="er.Main"
```

or inside IntelliJ:  
**Right-click Main.java → Run 'Main'**

---

## 🏗 Architecture Overview

### Components
- **SimulationEngine** – central event loop  
- **Event** – abstract class for scheduled actions  
- **ArrivalEvent / ServiceEndEvent** – main event types  
- **Patient** – entity with severity and arrival time  
- **EDQueue** – priority-based queue  
- **Doctor** – server resource  
- **TriageNurse** – assigns severity  

### UML Mapping
- Matches M1 class diagram (Patient, Events, Resources)
- Expanded for Java conventions (Event subclasses)

---

## 🗂 Project Board

Public project board:  
**https://github.com/users/ShennaT101/projects/1**

Columns:
- **To Do** – arrival model, service model, scheduling  
- **In Progress** – event loop implementation  
- **Done** – core class structure, repo setup, README

---

## 📸 Simulation Evidence (Screenshots in repo)
Files located in `/screenshots`:
- board_overview.png
- simulation_run1.png
- simulation_run2.png
- simulation_run3.png

---

## 📅 Next Steps
- Complete event dispatcher
- Implement distribution models
- Validate performance vs. literature
- Prepare Milestone 3 full system

---

## 📘 Acknowledgments
Kennesaw State University — CS 4632 Modeling & Simulation  
