# 🚀 How to Run the Project

## 1. Prerequisites

Make sure you have:

- Java 17+
- Maven
- Node is NOT required (frontend is plain HTML)
- Python (optional, for static server)
---

## Run Backend (Spring Boot)

From the simulation/ folder:


```bash
mvn clean install
mvn spring-boot:run
```
Backend will start on:
```
http://localhost:8080
```
---

## 3. Run Frontend 
### Python static server

From frontend/ folder:
```
python -m http.server 5500
```

Open in browser:
```
http://localhost:5500
```

---

## 4. Start the Simulation
Once UI is open:

Click:
- Start → begins simulation
- Stop → pauses simulation

Or use API directly:
```
POST http://localhost:8080/api/simulation/start
POST http://localhost:8080/api/simulation/stop
```

---

## 5. Verify It Works
You should see:

- Live time increasing
- Net load updating
- Battery SOC changing
- 24-hour chart moving