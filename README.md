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

* **Encapsulation** – Each module manages its own data and exposes controlled interfaces
* **Inheritance & Abstraction** – Base classes (e.g., `EnergySource`, `Crop`) define shared behavior
* **Composition** – Systems are built using "has-a" relationships rather than deep inheritance
* **Polymorphism** – Interfaces allow flexible and dynamic interaction between components
* **Exception Handling** – Custom exceptions ensure stable simulation under failure conditions

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
