# Climate-Controlled Agriculture Simulator (Java)

## Overview

This project is a Java-based simulation of a climate-controlled agricultural system designed using object-oriented programming principles.

The simulator models interactions between environmental conditions, energy systems, water management, and crop growth to maintain optimal conditions within a greenhouse environment.

---

## Features

* Simulation of environmental parameters (temperature, soil moisture, etc.)
* Energy management system (battery, energy sources)
* Water management and irrigation control
* Crop growth modeling
* Modular and extensible system design
* Robust exception handling for system stability

---

## Technologies Used

* Java
* Object-Oriented Programming (OOP)

---

## System Design

The system follows a modular and decentralized architecture where each subsystem operates independently and interacts through well-defined interfaces.

### Core Components

* **Environment** – Manages temperature, humidity, and soil conditions
* **Energy System** – Handles energy generation and storage (Battery, SolarPanel, etc.)
* **Water System** – Controls water storage, flow, and irrigation
* **Crop System** – Models plant growth and lifecycle

This design ensures low coupling, high cohesion, and scalability.

---

## OOP Design Principles

The system is designed using core object-oriented principles applied in a practical, system-level context:

- **Encapsulation & Information Hiding**  
  Each subsystem (Energy, Water, Crop, Environment) manages its internal state independently and exposes only necessary interfaces.

- **Inheritance & Abstraction**  
  Abstract classes such as `EnergySource` and `Crop` define common behavior, while specialized subclasses implement specific functionality.

- **Composition (Primary Design Pattern)**  
  The system heavily relies on composition (e.g., `EnergyManager` contains `Battery` and multiple `EnergySource` objects), enabling flexibility and modularity.

- **Polymorphism**  
  Interfaces and base classes allow components to interact generically while supporting multiple implementations at runtime.

- **Exception Handling**  
  Custom exceptions (e.g., energy or water shortages) ensure robust handling of failure scenarios without breaking system flow.

- **Extensibility (Open–Closed Principle)**  
  New components (energy sources, crops, devices) can be added without modifying existing code, making the system scalable.

---

## Project Structure

```
src/
 ├── Main.java
 ├── Environment.java
 ├── WaterManager.java
 ├── EnergyManager.java
 ├── Crop.java
 ├── Controller.java
```

---

## How to Compile

```bash
javac src/*.java
```

---

## How to Run

```bash
java -cp src Main
```

---

## Key Highlights

* Modular and extensible system architecture
* Decentralized design with independent subsystems
* Realistic simulation of energy–water–environment interactions
* Strong application of object-oriented design principles
* Designed following scalable software engineering practices

---

## Author

Mehul Kapoor
