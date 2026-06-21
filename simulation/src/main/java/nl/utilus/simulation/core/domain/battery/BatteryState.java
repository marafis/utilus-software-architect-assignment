package nl.utilus.simulation.core.domain.battery;

public record BatteryState(
        double capacityKwh,
        double maxChargeKw,
        double maxDischargeKw,
        double efficiency,
        double currentSocKwh
) {

    public BatteryState(double capacityKwh, double maxChargeKw, double maxDischargeKw, double efficiency) {
        this(capacityKwh, maxChargeKw, maxDischargeKw, efficiency, capacityKwh / 2.0);
    }

    public double getSocKwh() {
        return currentSocKwh;
    }

    public double getCapacityKwh() {
        return capacityKwh;
    }
}