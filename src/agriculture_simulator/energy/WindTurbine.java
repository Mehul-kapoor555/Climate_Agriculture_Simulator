package agriculture_simulator.energy;

import agriculture_simulator.weather.WeatherCondition;

public class WindTurbine extends EnergySource {

    public WindTurbine(double capacity) { 
        super(capacity); 
    }
  
    @Override
    public double generate(WeatherCondition weather) {
        double w = Math.max(0.0, Math.min(1.0, weather.getWindSpeed()));
        return getCapacity() * w;
    }
}
