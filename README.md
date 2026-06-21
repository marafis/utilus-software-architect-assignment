# 📘 Utilus Software Architect Assignment — Energy Simulation System

## 1. Project Overview

This project implements a **time-based energy simulation system** for a neighbourhood consisting of houses, distributed energy assets, public EV chargers, and a neighbourhood battery.

The system simulates:
- Energy consumption and generation over time
- Weather and seasonal influence
- Battery-based peak shaving optimization
- Real-time and historical energy behavior

A lightweight frontend visualizes the simulation using **HTML + Chart.js**, while the backend is built using **Spring Boot**.

---

## 2. System Design

The system follows a clean layered architecture:

Frontend (HTML + Chart.js)  
↓  
REST API (Spring Boot Controllers)  
↓  
Application Layer (SimulationService)  
↓  
Simulation Engine (Core computation logic)  
↓  
Domain Model (Houses, Assets, Battery, Weather)  
↓  
State Storage (HistoryStore)

This separation ensures:
- Clear responsibility boundaries
- High extensibility
- Testable simulation logic
- Maintainable architecture

---

## 3. Domain Model

Core domain components:

### Neighborhood
- Contains houses and public EV chargers
- Optional neighbourhood battery

### House
- Contains a list of energy assets

### Asset (interface)
Types:
- PVAsset → energy generation (solar)
- HeatPumpAsset → energy consumption (temperature-dependent)
- EVChargerAsset → energy consumption

### Battery
- State of Charge (kWh)
- Max charge/discharge power (kW)
- Peak shaving control strategy

### Weather
- Deterministic weather model (seed-based)
- Impacts:
    - PV generation
    - Heat pump consumption

### HistoryStore
- Stores time-series net load values
- Used for 24-hour visualization

---

## 4. Simulation Flow

Each simulation tick performs:

1. Generate deterministic weather (fixed seed)
2. Build simulation context (time + weather)
3. Compute asset-level power:
    - PV generates power (negative load)
    - Household + EV + heat pump consume power
4. Aggregate total load
5. Apply neighbourhood battery (peak shaving)
6. Compute final net load
7. Store result in HistoryStore
8. Advance simulation clock (15-minute step)

This ensures deterministic and reproducible simulation behavior.

---

## 5. REST API

- `GET /api/simulation/state` → current simulation state
- `GET /api/simulation/history` → 24-hour energy history
- `POST /api/simulation/start` → start simulation
- `POST /api/simulation/stop` → stop simulation
- `POST /api/simulation/tick` → manual simulation step

---

## 6. Frontend

A minimal frontend built with **HTML + Chart.js** provides:

### Features:
- Live simulation state (time, net load, battery SOC)
- Auto-refresh every second
- 24-hour historical energy chart
- Start / Stop controls

### Design choice:
No Angular/React used to ensure:
- Zero setup complexity
- Instant execution for reviewers
- Focus remains on backend architecture

---

## 7. Assumptions

- Simulation starts at: `2025-01-01 00:00`
- Time step: 15 minutes
- PV production uses simplified weather factor
- Heat pump consumption depends on temperature (simplified model)
- EV chargers use simplified consumption model
- Battery efficiency simplified (optional or ignored)
- Energy flow aggregated at neighbourhood level
- Simulation is deterministic using fixed random seed (42)

---

## 8. Tradeoffs

### ✔ Simplicity over realism
Physical accuracy is reduced in favor of clarity and correctness.

### ✔ In-memory storage
HistoryStore is not persisted to database (simplifies design).

### ✔ Minimal frontend
No frontend framework used to reduce complexity and setup time.

### ✔ Single-threaded simulation
No distributed or concurrent simulation engine used (prototype scope).

---

## 9. Testing Strategy

Due to time constraints, automated tests were not included.

Priority was given to:
- Correctness of simulation logic
- Energy accounting consistency
- Battery peak-shaving behavior
- End-to-end API + UI integration

The system is deterministic (fixed random seed), making it suitable for future unit and regression testing.

In a production-ready version, I would add:
- Unit tests for SimulationEngine (time progression, energy calculations)
- Battery state transition tests (SOC bounds, charge/discharge logic)
- Property-based tests for deterministic simulation behavior
- Integration tests for REST API endpoints

---
## 10. Configuration (Note on Implementation)

Due to time constraints, the neighbourhood configuration was implemented directly in code rather than being fully externalized into a JSON/YAML configuration file.

However, the system is designed with a clear separation of concerns through the NeighborhoodConfig and NeighborhoodFactory components, making it straightforward to replace the code-based configuration with external configuration (JSON/YAML or Spring configuration properties) without changes to the core simulation engine.

The current implementation still ensures:
- Exactly 30 houses
- Exactly 6 public EV chargers
- Deterministic behavior using a fixed random seed (42)
- Configurable asset distribution logic (PV, Heat Pump, EV Charger ratios)

This design choice was made to prioritize core simulation correctness, energy accounting, and visualization within the limited time window.

---
## 11. AI Usage Log

AI tools (ChatGPT) were used as a development assistant for:

- Domain modeling (Battery, Assets, Simulation Engine)
- Debugging Java design issues (state handling, records vs classes)
- Frontend visualization support (Chart.js integration)
- Documentation structuring and clarity improvements

All final implementation decisions, tradeoffs, and code were reviewed and manually implemented.

---

## 12. Key Strengths of This Implementation
- Clean layered architecture
- Deterministic simulation engine
- Extensible domain model
- Battery-based peak shaving implementation
- RESTful API design
- Lightweight real-time visualization
- Clear separation of concerns
---

## 14. Future Improvements
- Add persistent storage (PostgreSQL)
- Introduce event-driven architecture (Kafka/RabbitMQ)
- Replace polling UI with WebSockets
- Improve battery physics model
- Add per-house visualization
- Integrate real weather API
- neighbourhood configuration in json

--- 

