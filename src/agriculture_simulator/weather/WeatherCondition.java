package agriculture_simulator.weather; 

public final class WeatherCondition {
    private final double temperature;  
    private final double humidity;     
    private final double sunlight;     
    private final double windSpeed;    
    private final boolean rainfall;    

    public WeatherCondition(double temperature, double humidity, double sunlight,
                            double windSpeed, boolean rainfall) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.sunlight = sunlight;
        this.windSpeed = windSpeed;
        this.rainfall = rainfall;
    }

    public double getTemperature() { return temperature; }
    public double getHumidity() { return humidity; }
    public double getSunlight() { return sunlight; }
    public double getWindSpeed() { return windSpeed; }
    public boolean isRaining() { return rainfall; }

    @Override
    public String toString() {
        return String.format(
            "Outside Weather [Temp: %.1f°C | Humidity: %.1f%% | Sunlight: %.2f | Wind: %.1f m/s | Rain: %s]",
            temperature, humidity, sunlight, windSpeed, rainfall ? "Yes" : "No" );
    }
}
