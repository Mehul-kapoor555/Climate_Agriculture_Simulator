package agriculture_simulator.setup; 

import agriculture_simulator.greenhouse.ConcreteGreenhouse;
import agriculture_simulator.core.*;
import agriculture_simulator.climate.*;
import agriculture_simulator.energy.*;
import agriculture_simulator.water.*;
import agriculture_simulator.crop.*;
import agriculture_simulator.weather.*;

import java.util.*;

/**
 * Factory: constructs example greenhouses with correct wiring for per-greenhouse
 * energy & water managers, environment, climate control, devices and crops.
 */
public final class Factory {

    private static final Random RNG = new Random();

    private static ConcreteGreenhouse createGreenhouse(String id, List<Growable> growables,
                                                      double initTemp, double initSoilMoisture,
                                                      double batteryCapacity, double solarCapacity, 
                                                      double windCapacity, double tankCapacity) {
        // --- 1) ENERGY SYSTEM ---
        Battery battery = new Battery(batteryCapacity);
        EnergyManager energyManager = new EnergyManager(battery);
        energyManager.registerSource(new SolarPanel(solarCapacity));
        energyManager.registerSource(new WindTurbine(windCapacity));
        EnergyBroker energyBroker = energyManager; 

        // --- 1) WATER SYSTEM ---
        WaterTank tank = new WaterTank(tankCapacity);
        WaterManager waterManager = new WaterManager(tank, energyBroker, 0.35, 1.2);
        WaterBroker waterBroker = waterManager;

        // --- 3) ENVIRONMENT ---
        Environment env = new Environment(initTemp, initSoilMoisture);

        // --- 4) DEVICES ---
        List<Device> devices = new ArrayList<>();

        // Heater
        double heaterTarget = initTemp + 3.0;
        double heaterTolerance = 1.0;
        double heaterKWhPerDegree = 0.6; 
        devices.add(new Heater(energyBroker, heaterTarget, heaterTolerance, heaterKWhPerDegree));

        // Cooler
        double coolerTarget = Math.max(18.0, initTemp - 2.0);
        double coolerTolerance = 1.0;
        double coolerKWhPerDegree = 0.7;
        devices.add(new Cooler(energyBroker, coolerTarget, coolerTolerance, coolerKWhPerDegree));

        // Irrigation
        double irrigTargetSoil = Math.min(80.0, initSoilMoisture); 
        double litersPerAction = 5.0;      
        double irrigKWhPerAction = 0.88;   
        devices.add(new Irrigation(energyBroker, waterBroker, irrigTargetSoil, litersPerAction, irrigKWhPerAction));

        // --- 5) CLIMATE SYSTEM ---
        ClimateControlSystem climate = new ClimateControlSystem(devices, energyBroker, waterBroker);

        // --- 6) WEATHER SIMULATOR ---
        WeatherSimulator ws = new WeatherSimulator();

        // --- 7) GREENHOUSE ---
        return new ConcreteGreenhouse(id, env, climate, energyBroker, waterBroker, ws, growables);
    }

    // --- EXAMPLE GREENHOUSES ---
    private static List<ConcreteGreenhouse> createExampleGreenhouses() {
        List<ConcreteGreenhouse> out = new ArrayList<>();

        out.add(createGreenhouse(
                "GH-1",
                List.of(new Tomato()),
                20.0 + RNG.nextDouble() * 3.0,
                40.0 + RNG.nextDouble() * 10.0,
                100.0, 35.0, 60.0, 200.0
        ));

        out.add(createGreenhouse(
                "GH-2",
                List.of(new Rice(), new Wheat()),
                18.0 + RNG.nextDouble() * 3.0,
                45.0 + RNG.nextDouble() * 8.0,
                120.0, 40.0, 55.0, 250.0
        ));

        out.add(createGreenhouse(
                "GH-3",
                List.of(new Tomato(), new SoyBean()),
                19.0 + RNG.nextDouble() * 3.0,
                42.0 + RNG.nextDouble() * 10.0,
                110.0, 50.0, 45.0, 220.0
        ));

        return out;
    }

    public static void runSimulation(int daysPerGreenhouse) {
        List<ConcreteGreenhouse> greenhouses = createExampleGreenhouses();
        WeatherSimulator outsideSim = new WeatherSimulator();
        EnvironmentSimulator envSim = new EnvironmentSimulator();

        System.out.println("=== Starting simulation (each greenhouse runs " + daysPerGreenhouse + " days in sequence) ===\n");

        for (ConcreteGreenhouse gh : greenhouses) {
            System.out.println("==== Greenhouse: " + gh.getId() + " ====\n");

            for (int day = 1; day <= daysPerGreenhouse; ++day) {
                System.out.println("            ------- Day " + day + " -------");

                // Generate outside weather for the day
                WeatherCondition outside = outsideSim.nextDay();
                // System.out.println(outside);

                // Generate inside environment
                Environment env = envSim.newEnvironment();
                System.out.println(env);
                
                // Run one simulation day (harvest, first fill, climate, growth)
                gh.simulateDay(outside, env);

                // Print resource summaries
                System.out.printf("%n%n[Battery] Available: %.2f / %.2f kWh%n", 
                        gh.getEnergyBroker().getAvailable(), gh.getEnergyBroker().getBatteryCapacity());
                System.out.printf("[WaterTank] Available: %.2f / %.2f L%n%n",
                        gh.getWaterBroker().getWaterLevel(), gh.getWaterBroker().getTankCapacity());

                // Print crop statuses
                System.out.println("[Growables] : ");
                for (Growable g : gh.getGrowables()) {
                    if (g instanceof Crop) {
                        Crop c = (Crop) g;         // downcast
                        System.out.println(c.getStatus());
                        }
                }

                System.out.println(); // blank line between days
            }

            System.out.println("========== End of greenhouse: " + gh.getId() + " ==========\n");
        }

        System.out.println("=========== All greenhouses simulated ==========");
    
    }
}
