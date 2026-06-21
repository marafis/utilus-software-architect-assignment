package nl.utilus.simulation.core.service;

import nl.utilus.simulation.api.dto.HistoryDto;
import nl.utilus.simulation.api.dto.SimulationStateDto;
import nl.utilus.simulation.core.domain.Neighborhood;
import nl.utilus.simulation.core.domain.battery.BatteryControlStrategy;
import nl.utilus.simulation.core.domain.battery.BatteryState;
import nl.utilus.simulation.core.domain.battery.NeighborhoodBattery;
import nl.utilus.simulation.core.domain.battery.PeakShavingStrategy;
import nl.utilus.simulation.core.engine.*;
import nl.utilus.simulation.core.factory.NeighborhoodFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimulationService {
    private final SimulationEngine engine;
    private final HistoryStore historyStore;
    private final NeighborhoodBattery battery;

    private boolean running = false;

    public SimulationService() {
        Neighborhood neighborhood = NeighborhoodFactory.create(42);

        this.historyStore = new HistoryStore();
        BatteryState state = new BatteryState(50,5,5,0.95, 25);
        BatteryControlStrategy strategy = new PeakShavingStrategy(20);
        this.battery = new NeighborhoodBattery(state, strategy);
        this.engine = new SimulationEngine(neighborhood, historyStore, battery);
    }

    public void tick() {
        engine.tick();
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }


    public SimulationStateDto getState() {
        SimulationStateDto engineState =  engine.getCurrentState();
        return new SimulationStateDto(
            engineState.time(),
            engineState.weather(),
            engineState.netLoadKw(),
            getBatterySoc(),
            getBatteryPower()
        );
    }

    public List<HistoryDto> getHistory() {
        return historyStore.getLast24Hours();
    }

    public double getBatterySoc() {
        return battery.getState().getSocKwh();
    }

    public double getBatteryPower() {
        return battery.getLastPowerKw();
    }
}
