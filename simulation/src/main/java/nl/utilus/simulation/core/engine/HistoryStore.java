package nl.utilus.simulation.core.engine;

import nl.utilus.simulation.api.dto.HistoryDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryStore {

    private final List<HistoryDto> points = new ArrayList<>();

    public void record(LocalDateTime time, double netLoadKw) {
        points.add(new HistoryDto(time, netLoadKw));

        // keep last 24h only (optional but smart)
        if (points.size() > 2000) {
            points.remove(0);
        }
    }

    public List<HistoryDto> getLast24Hours() {
        return points;
    }

}
