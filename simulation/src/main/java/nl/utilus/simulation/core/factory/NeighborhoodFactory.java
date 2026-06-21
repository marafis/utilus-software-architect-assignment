package nl.utilus.simulation.core.factory;

import nl.utilus.simulation.core.domain.Asset;
import nl.utilus.simulation.core.domain.House;
import nl.utilus.simulation.core.domain.Neighborhood;
import nl.utilus.simulation.core.domain.assets.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NeighborhoodFactory {
    public static Neighborhood create(int seed) {

        Random random = new Random(seed);

        List<House> houses = new ArrayList<>();

        for (int i = 0; i < 30; i++) {

            List<Asset> assets = new ArrayList<>();

            // Base load always present
            assets.add(new BaseLoadAsset());

            // 40% PV
            if (random.nextDouble() < 0.4) {
                assets.add(new PvAsset());
            }

            // 30% Heat Pump
            if (random.nextDouble() < 0.3) {
                assets.add(new HeatPumpAsset());
            }

            // 20% Home EV
            if (random.nextDouble() < 0.2) {
                assets.add(new HomeEvChargerAsset());
            }

            houses.add(new House("H-" + i, assets));
        }

        List<Asset> publicChargers = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            publicChargers.add(new PublicEvChargerAsset());
        }

        return new Neighborhood(houses, publicChargers);
    }
}
