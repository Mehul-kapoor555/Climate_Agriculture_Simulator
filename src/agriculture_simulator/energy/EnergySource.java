package agriculture_simulator.energy;

import agriculture_simulator.weather.WeatherCondition;

public abstract class EnergySource {
    private final double capacity;
    
    public EnergySource(double capacity) {
        this.capacity = capacity;
    }

    public abstract double generate(WeatherCondition weather);

    public final double getCapacity() { 
        return capacity;
    }
}
