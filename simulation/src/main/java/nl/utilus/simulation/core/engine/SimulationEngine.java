package nl.utilus.simulation.core.engine;

import lombok.Getter;
import nl.utilus.simulation.api.dto.SimulationStateDto;
import nl.utilus.simulation.core.domain.assets.PvAsset;
import nl.utilus.simulation.core.domain.*;
import nl.utilus.simulation.core.domain.battery.NeighborhoodBattery;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

public class SimulationEngine {
    @Getter
    private LocalDateTime currentTime = LocalDateTime.of(2025, 1, 1, 0, 0);
    private final Duration step = Duration.ofMinutes(15);
    private final Random random = new Random(42);

    private final Neighborhood neighborhood;
    private final HistoryStore historyStore;
    private final NeighborhoodBattery battery;
    private Weather lastWeather;
    private double lastNetLoad;


    public SimulationEngine(Neighborhood neighborhood, HistoryStore historyStore, NeighborhoodBattery battery) {
        this.neighborhood = neighborhood;
        this.historyStore = historyStore;
        this.battery = battery;
    }

    public void tick() {

        lastWeather = WeatherService.generate(currentTime, random);

        SimulationContext context = new SimulationContext(currentTime, lastWeather, random);

        double hours = step.toMinutes() / 60.0;

        double totalLoad = 0;
        double totalPv = 0;

        for (House house : neighborhood.houses()) {
            for (Asset asset : house.assets()) {

                double power = asset.getPowerKw(context);

                asset.accumulateEnergy(hours, context);

                if (asset instanceof PvAsset) {
                    totalPv += power;
                } else {
                    totalLoad += power;
                }
            }
        }

        for (Asset asset : neighborhood.publicChargers()) {
            double power = asset.getPowerKw(context);
            asset.accumulateEnergy(hours, context);
            totalLoad += power;
        }

        lastNetLoad = totalLoad - totalPv;

        double batteryPower = battery.step(lastNetLoad, context, hours);
        double finalNetLoad = lastNetLoad + batteryPower;

        historyStore.record(currentTime, finalNetLoad);

        currentTime = currentTime.plus(step);
    }

    public SimulationStateDto getCurrentState() {
        return new SimulationStateDto(currentTime, lastWeather, lastNetLoad, battery.getState().getSocKwh(), battery.getLastPowerKw());
    }
}
