package nl.utilus.simulation.core.domain.assets;

import nl.utilus.simulation.core.domain.AbstractAsset;
import nl.utilus.simulation.core.domain.AssetType;
import nl.utilus.simulation.core.domain.SimulationContext;

public class PvAsset extends AbstractAsset {

    @Override
    public AssetType getType() {
        return AssetType.PV;
    }

    @Override
    public double getPowerKw(SimulationContext context) {

        int hour = context.time().getHour();

        if (hour < 6 || hour > 18) return 0;

        double base = 3.0;
        double cloud = context.weather().cloudFactor();

        double seasonFactor =
                switch (context.weather().season()) {
                    case SUMMER -> 1.2;
                    case SPRING, AUTUMN -> 1.0;
                    case WINTER -> 0.6;
                };

        return base * cloud * seasonFactor;
    }

    @Override
    public void accumulateEnergy(double hours, SimulationContext context) {
        addEnergy(getPowerKw(context) * hours);
    }
}
