package nl.utilus.simulation.core.domain.assets;

import nl.utilus.simulation.core.domain.AbstractAsset;
import nl.utilus.simulation.core.domain.AssetType;
import nl.utilus.simulation.core.domain.SimulationContext;

public class HomeEvChargerAsset extends AbstractAsset {

    @Override
    public AssetType getType() {
        return AssetType.HOME_EV;
    }

    @Override
    public double getPowerKw(SimulationContext context) {
        int hour = context.time().getHour();
        return (hour >= 18 && hour <= 22) ? 7.0 : 0.0;
    }

    @Override
    public void accumulateEnergy(double hours, SimulationContext context) {
        addEnergy(getPowerKw(context) * hours);
    }
}
