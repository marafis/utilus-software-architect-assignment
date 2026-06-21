package nl.utilus.simulation.core.domain.assets;

import nl.utilus.simulation.core.domain.AbstractAsset;
import nl.utilus.simulation.core.domain.AssetType;
import nl.utilus.simulation.core.domain.SimulationContext;

public class HeatPumpAsset extends AbstractAsset {

    @Override
    public AssetType getType() {
        return AssetType.HEAT_PUMP;
    }

    @Override
    public double getPowerKw(SimulationContext context) {
        double temp = context.weather().temperature();
        return Math.max(0, (18 - temp)) * 0.3;
    }

    @Override
    public void accumulateEnergy(double hours, SimulationContext context) {
        addEnergy(getPowerKw(context) * hours);
    }
}
