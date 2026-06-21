package nl.utilus.simulation.core.runner;

import nl.utilus.simulation.core.service.SimulationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimulationRunner {

    private final SimulationService service;

    public SimulationRunner(SimulationService service) {
        this.service = service;
    }

    @Scheduled(fixedRate = 1000)
    public void run() {
        if (service.isRunning()) {
            service.tick();
        }
    }
}
