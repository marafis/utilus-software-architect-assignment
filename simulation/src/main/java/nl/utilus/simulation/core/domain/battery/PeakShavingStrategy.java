package nl.utilus.simulation.core.domain.battery;

import nl.utilus.simulation.core.domain.SimulationContext;

public record PeakShavingStrategy(double thresholdKw) implements BatteryControlStrategy {

    @Override
    public double decidePowerKw(double netLoadKw,
                                BatteryState state,
                                SimulationContext context) {

        // high load
        if (netLoadKw > thresholdKw && state.getSocKwh() > 0) {
            return -Math.min(5.0, state.getSocKwh());
        }

        // low load
        if (netLoadKw < thresholdKw && state.getSocKwh() < state.getCapacityKwh()) {
            return Math.min(5.0, state.getCapacityKwh() - state.getSocKwh());
        }

        return 0;
    }
}
