package nl.utilus.simulation.api;

import nl.utilus.simulation.api.dto.SimulationStateDto;
import nl.utilus.simulation.api.dto.HistoryDto;
import nl.utilus.simulation.core.service.SimulationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/simulation")
@CrossOrigin(origins = "*")
public class SimulationController {

    private final SimulationService service;

    public SimulationController(SimulationService service) {
        this.service = service;
    }

    @GetMapping("/state")
    public SimulationStateDto getState() {
        return service.getState();
    }

    @GetMapping("/history")
    public List<HistoryDto> getHistory() {
        return service.getHistory();
    }

    @PostMapping("/start")
    public ResponseEntity<Void> start() {
        service.start();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/stop")
    public ResponseEntity<Void> stop() {
        service.stop();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/tick")
    public ResponseEntity<Void> tick() {
        service.tick();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/status")
    public boolean status() {
        return service.isRunning();
    }
}
