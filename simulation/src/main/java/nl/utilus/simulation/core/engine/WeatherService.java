package nl.utilus.simulation.core.engine;

import nl.utilus.simulation.core.domain.Season;
import nl.utilus.simulation.core.domain.Weather;

import java.time.LocalDateTime;
import java.util.Random;

public class WeatherService {
    public static Weather generate(LocalDateTime time, Random random) {

        Season season = switch (time.getMonth()) {
            case DECEMBER, JANUARY, FEBRUARY -> Season.WINTER;
            case MARCH, APRIL, MAY -> Season.SPRING;
            case JUNE, JULY, AUGUST -> Season.SUMMER;
            default -> Season.AUTUMN;
        };

        double temp = switch (season) {
            case WINTER -> 5 + random.nextDouble() * 5;
            case SPRING, AUTUMN -> 8 + random.nextDouble() * 10;
            case SUMMER -> 18 + random.nextDouble() * 12;
        };

        double cloud = 0.3 + random.nextDouble() * 0.7;

        return new Weather(temp, cloud, season);
    }
}
