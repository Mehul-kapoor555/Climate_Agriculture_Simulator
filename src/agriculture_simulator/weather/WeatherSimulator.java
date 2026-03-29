package agriculture_simulator.weather;

import java.util.Random;

public class WeatherSimulator {
    private final Random rnd = new Random();

    // helper method to round any double to 2 decimal places
    private double round2(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    public WeatherCondition nextDay() {
        // temperature between 10°C and 35°C
        double temp = round2(10 + rnd.nextDouble() * 25.0);

        // humidity between 30% and 90%
        double humidity = round2(30 + rnd.nextDouble() * 60.0);

        // sunlight between 0..1
        double sunlight = round2(rnd.nextDouble());

        // wind speed between 0 and 1 
        double windSpeed = round2(rnd.nextDouble());

        // rainfall: true if rnd.nextDouble() > 0.5
        boolean rainfall = rnd.nextDouble() > 0.5;

        return new WeatherCondition(temp, humidity, sunlight, windSpeed, rainfall);
    }
}
