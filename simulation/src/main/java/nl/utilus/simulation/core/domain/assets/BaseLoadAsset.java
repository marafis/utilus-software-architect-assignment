package nl.utilus.simulation.core.domain.assets;

import nl.utilus.simulation.core.domain.AbstractAsset;
import nl.utilus.simulation.core.domain.AssetType;
import nl.utilus.simulation.core.domain.SimulationContext;

public class BaseLoadAsset extends AbstractAsset {

    @Override
    public AssetType getType() {
        return AssetType.BASE_LOAD;
    }

    @Override
    public double getPowerKw(SimulationContext context) {
        return 0.4 + context.random().nextDouble() * 0.8;
    }

    @Override
    public void accumulateEnergy(double hours, SimulationContext context) {
        addEnergy(getPowerKw(context) * hours);
    }
}
