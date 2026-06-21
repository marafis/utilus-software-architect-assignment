package nl.utilus.simulation.api.dto;

import java.time.LocalDateTime;

public class HistoryDto {
    private final LocalDateTime time;
    private final double netLoadKw;

    public HistoryDto(LocalDateTime time, double netLoadKw) {
        this.time = time;
        this.netLoadKw = netLoadKw;
    }

    public LocalDateTime getTime() { return time; }

    public double getNetLoadKw() { return netLoadKw; }
}
