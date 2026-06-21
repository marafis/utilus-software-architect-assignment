package nl.utilus.simulation.core.domain.battery;

import nl.utilus.simulation.core.domain.SimulationContext;

public interface BatteryControlStrategy {

    double decidePowerKw(
            double netLoadKw,
            BatteryState state,
            SimulationContext context
    );
}