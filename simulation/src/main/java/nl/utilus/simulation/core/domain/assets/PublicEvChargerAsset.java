package nl.utilus.simulation.core.domain.assets;

import nl.utilus.simulation.core.domain.AbstractAsset;
import nl.utilus.simulation.core.domain.AssetType;
import nl.utilus.simulation.core.domain.SimulationContext;

public class PublicEvChargerAsset extends AbstractAsset {

    @Override
    public AssetType getType() {
        return AssetType.PUBLIC_EV;
    }

    @Override
    public double getPowerKw(SimulationContext context) {
        boolean active = context.random().nextDouble() < 0.3;
        return active ? 11.0 : 0.0;
    }

    @Override
    public void accumulateEnergy(double hours, SimulationContext context) {
        addEnergy(getPowerKw(context) * hours);
    }
}
