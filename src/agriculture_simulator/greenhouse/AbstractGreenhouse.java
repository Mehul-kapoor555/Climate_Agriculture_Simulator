package agriculture_simulator.greenhouse;

import agriculture_simulator.core.Environment;
import agriculture_simulator.core.EnvironmentController;
import agriculture_simulator.core.EnvironmentView;
import agriculture_simulator.climate.ClimateControlSystem;
import agriculture_simulator.energy.EnergyBroker;
import agriculture_simulator.water.WaterBroker;
import agriculture_simulator.crop.Crop;
import agriculture_simulator.crop.Growable;
import agriculture_simulator.weather.WeatherCondition;
import agriculture_simulator.weather.WeatherSimulator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class AbstractGreenhouse {
    private final String id;
    private final Environment environment;
    private final ClimateControlSystem climate;
    private final EnergyBroker energyBroker;
    private final WaterBroker waterBroker;         
    private final WeatherSimulator weatherSimulator;
    private final List<Growable> growables;

    protected AbstractGreenhouse(String id,
                                 Environment environment,
                                 ClimateControlSystem climate,
                                 EnergyBroker energyBroker,
                                 WaterBroker waterBroker,
                                 WeatherSimulator weatherSimulator,
                                 List<Growable> growables) {
        this.id = id;
        this.environment = environment;
        this.climate = climate;
        this.energyBroker = energyBroker;
        this.waterBroker = waterBroker;
        this.weatherSimulator = weatherSimulator;
        this.growables = Collections.unmodifiableList(List.copyOf(growables));
    }

    public final void simulateDay(WeatherCondition outside, Environment environment) {
        // 1. Harvest energy (e.g. solar)
        energyBroker.harvestAll(outside);

        // 2. Ensure tank has initial water
        waterBroker.firstTankFill();

        EnvironmentController controller = environment;
        EnvironmentView view = environment;

        // 3. Adjust climate (devices mutate environment via EnvironmentController)
        climate.adjustAll(controller, view);

        // 4. Grow all plants / growables  (read-only access via EnvironmentView)
        for (Growable g : growables) g.grow(view);
    }

    public String getId() {
        return id;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public EnergyBroker getEnergyBroker() {
        return energyBroker;
    }

    public WaterBroker getWaterBroker() {
        return waterBroker;
    }

    public List<Growable> getGrowables() {
        return growables;
    }

}
