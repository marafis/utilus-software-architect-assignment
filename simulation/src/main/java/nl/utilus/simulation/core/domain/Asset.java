package nl.utilus.simulation.core.domain;

public interface Asset {
    String getId();
    AssetType getType();
    double getPowerKw(SimulationContext context);
    void accumulateEnergy(double hours, SimulationContext context);
    double getTotalEnergyKwh();
}
