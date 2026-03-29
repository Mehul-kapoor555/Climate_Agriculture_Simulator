# Climate-Controlled Agriculture Simulator (Java)

## Overview

This project is a Java-based simulation of a climate-controlled greenhouse system. It models the interaction between weather conditions, energy generation, water management, and crop growth using a decentralized and modular architecture.

The system simulates daily operations of a greenhouse, where environmental conditions are dynamically adjusted to support optimal crop growth.

---

## Simulation Flow

The system operates as a pipeline of interacting subsystems:

1. **Weather Simulation**
   External weather conditions are generated using `WeatherSimulator` and `WeatherCondition`, producing dynamic inputs such as temperature and environmental factors.

2. **Energy Generation**
   Based on weather conditions, the `EnergyManager` coordinates energy production from sources like `SolarPanel` and `WindTurbine`.

3. **Energy Storage**
   Generated energy is stored in a `Battery`, which is managed centrally by the energy subsystem.

4. **Water Management**
   The `WaterManager` uses available energy to pump water into the system.
   If insufficient water is available, it purchases water from an external grid.

5. **Climate Control System**
   Devices such as:

   * Heater
   * Cooler
   * Irrigation System

   regulate internal greenhouse conditions using available energy and water.

   Note: The irrigation system is the only component that consumes both water and energy.

6. **Crop Growth Simulation**
   Crops evaluate internal conditions (temperature, soil moisture) and update their growth, health, and lifecycle state accordingly.

---

## System Design

The simulator follows a **decentralized architecture**, where each subsystem operates independently and communicates only through well-defined interfaces.

### Core Subsystems

* **Weather System** – Generates external environmental conditions
* **Energy System** – Handles energy generation and storage
* **Water System** – Manages water flow and procurement
* **Climate Control System** – Regulates internal greenhouse conditions
* **Crop System** – Simulates plant growth and response

This design ensures high modularity, scalability, and separation of concerns.

---

## OOP Design Principles

The project applies object-oriented principles in a system-level context:

* **Encapsulation & Information Hiding**
  Each subsystem maintains its internal state and exposes controlled interfaces.

* **Abstraction & Inheritance**
  Abstract classes (e.g., `EnergySource`, `Crop`) define shared behavior across components.

* **Composition (Primary Design Approach)**
  Systems are built using "has-a" relationships (e.g., `EnergyManager` contains `Battery` and multiple energy sources).

* **Polymorphism**
  Interfaces allow interchangeable components and dynamic behavior at runtime.

* **Exception Handling**
  Custom exceptions ensure robustness under conditions such as energy or water shortages.

* **Extensibility (Open–Closed Principle)**
  New components (crops, devices, energy sources) can be added without modifying existing code.

---

## Example Simulation Output

```text
==== Greenhouse: GH-3 ====

            ------- Day 1 -------
Environment [Temperature: 27.51°C, Soil Moisture: 34.63%]

[EnergyManager] Harvesting energy from all sources...
                SolarPanel generated: 7.00 kWh (stored: 7.00 kWh)
                WindTurbine generated: 44.10 kWh (stored: 44.10 kWh)

[Battery] Now charged to 51.10 / 110.00 kWh
[EnergyManager] Total harvested today: 51.10 kWh

[WaterManager] Not enough energy (92.40 kWh needed). Purchased 77.00 L of water from grid for 154.00 Euros.
[Water Tank] Tank at 77.00 / 220.00 L.

[Cooler] Temperature -8.32 (used 5.82 kWh)
[Irrigation] Soil moisture +50.21 (used 31.15 L water, 1.56 kWh)


[Battery] Available: 43.72 / 110.00 kWh
[WaterTank] Available: 45.85 / 220.00 L

[Growables] :
Tomato | Health: 70.5% | Moisture: 59.0% | Stage: 0.7
SoyBean | Health: 72.0% | Moisture: 59.0% | Stage: 1.0

```

---

## Project Structure

The project is organized into modular subsystems:

- **Weather System** – Generates external conditions (`WeatherSimulator`, `WeatherCondition`)
- **Energy System** – Manages energy generation and storage (`EnergyManager`, `SolarPanel`, `WindTurbine`, `Battery`)
- **Water System** – Handles water flow and procurement (`WaterManager`, `WaterTank`)
- **Climate Control System** – Regulates internal conditions (`Heater`, `Cooler`, `Irrigation`)
- **Crop System** – Models plant growth and lifecycle (`Crop`, specific crop types)

Each subsystem is implemented as an independent module and interacts through interfaces.

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

* Fully decentralized system architecture
* Independent yet interacting subsystems
* Realistic simulation of energy–water–climate dependencies
* Strong application of OOP principles in system design
* Highly modular and extensible implementation

---

## Author

Mehul Kapoor
