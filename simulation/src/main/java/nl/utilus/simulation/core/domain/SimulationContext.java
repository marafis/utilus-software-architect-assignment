package nl.utilus.simulation.core.domain;

import java.time.LocalDateTime;
import java.util.Random;

public record SimulationContext(LocalDateTime time, Weather weather, Random random) {
}
