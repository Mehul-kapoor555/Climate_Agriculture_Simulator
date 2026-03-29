package agriculture_simulator.greenhouse;

import agriculture_simulator.core.Environment;
import agriculture_simulator.climate.ClimateControlSystem;
import agriculture_simulator.energy.EnergyBroker;
import agriculture_simulator.water.WaterBroker;
import agriculture_simulator.weather.WeatherSimulator;
import agriculture_simulator.crop.Growable;

import java.util.List;

public class ConcreteGreenhouse extends AbstractGreenhouse {
    public ConcreteGreenhouse(String id,
                              Environment environment,
                              ClimateControlSystem climate,
                              EnergyBroker energyBroker,
                              WaterBroker waterBroker,
                              WeatherSimulator weatherSimulator,
                              List<Growable> growables) {
        super(id, environment, climate, energyBroker, waterBroker, weatherSimulator, growables);
    }
}
 