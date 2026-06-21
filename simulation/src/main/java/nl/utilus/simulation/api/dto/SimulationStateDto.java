package nl.utilus.simulation.api.dto;

import nl.utilus.simulation.core.domain.Weather;

import java.time.LocalDateTime;

public record SimulationStateDto(LocalDateTime time, Weather weather, double netLoadKw, double batterySocKwh, double batteryPowerKw) {
}