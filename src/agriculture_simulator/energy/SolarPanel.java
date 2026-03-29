package agriculture_simulator.energy;

import agriculture_simulator.weather.WeatherCondition;

public class SolarPanel extends EnergySource {

    public SolarPanel(double capacity) { 
        super(capacity); 
    }
  
    @Override
    public double generate(WeatherCondition weather) { 
        double s = Math.max(0.0, Math.min(1.0, weather.getSunlight()));
        return getCapacity() * s;
    }
}
