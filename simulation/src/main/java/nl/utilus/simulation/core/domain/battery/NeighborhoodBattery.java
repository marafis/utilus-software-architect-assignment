package nl.utilus.simulation.core.domain.battery;

import nl.utilus.simulation.core.domain.SimulationContext;

public class NeighborhoodBattery {
        BatteryState state;
        BatteryControlStrategy strategy;
        double lastPowerKw;

    public NeighborhoodBattery(BatteryState state, BatteryControlStrategy strategy) {
        this.state = state;
        this.strategy = strategy;
    }

    public double step(double netLoadKw, SimulationContext context, double hours) {
        double powerKw = strategy.decidePowerKw(netLoadKw, state, context);
        apply(powerKw, hours);
        return powerKw;
    }

    private void apply(double powerKw, double hours) {
        double energyKwh = powerKw * hours;
        double newSoc = state.getSocKwh() - energyKwh;
        newSoc = Math.max(0, Math.min(state.getCapacityKwh(), newSoc));
        state = new BatteryState(
                state.getCapacityKwh(),
                state.maxChargeKw(),
                state.maxDischargeKw(),
                state.efficiency(),
                newSoc
        );
    }

    public BatteryState getState() {
        return state;
    }

    public double getLastPowerKw() {
        return lastPowerKw;
    }
}