package nl.utilus.simulation.core.domain;

import java.util.UUID;

public abstract class AbstractAsset implements Asset {
    private final String id = UUID.randomUUID().toString();
    private double totalEnergy;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getTotalEnergyKwh() {
        return totalEnergy;
    }

    protected void addEnergy(double energy) {
        this.totalEnergy += energy;
    }
}
