package nl.utilus.simulation.core.domain;

import java.util.List;

public record Neighborhood(List<House> houses, List<Asset> publicChargers) {
}
