package agriculture_simulator.energy;

import agriculture_simulator.weather.WeatherCondition;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections; 

public class EnergyManager implements EnergyBroker {
    private final Battery battery;
    private final List<EnergySource> sources = new ArrayList<>();
    private static final double PRICE_PER_KWH = 5.0; 

    public EnergyManager(Battery battery) {
        this.battery = battery;
    }

    public void registerSource(EnergySource source) {
    sources.add(source);
    }

    @Override
    public List<EnergySource> getSources() {
        return Collections.unmodifiableList(sources);
    }

    @Override
    public void harvestAll(WeatherCondition outside) {
        double totalHarvested = 0.0;

        System.out.println("[EnergyManager] Harvesting energy from all sources...");

        for (EnergySource source : sources) {
            double generated = source.generate(outside);
            double stored = battery.charge(generated);

            System.out.printf("                %-10s generated: %.2f kWh (stored: %.2f kWh)%n",
                    source.getClass().getSimpleName(), generated, stored);

            totalHarvested += stored;
        }

        System.out.printf("%n[Battery] Now charged to %.2f / %.2f kWh%n",
                getAvailable(), getBatteryCapacity());

        System.out.printf("[EnergyManager] Total harvested today: %.2f kWh%n%n", totalHarvested);
    }

 
    @Override
    public boolean tryConsume(double amountKWh) {
        if (battery.getStored() >= amountKWh) {
            battery.discharge(amountKWh);
            return true;
        }
        return false;
    }

    @Override
    public double getAvailable() { 
        return battery.getStored(); 
    }

    @Override 
    public double getBatteryCapacity() {
        return battery.getCapacity();
    }

    @Override
    public double getEnergyPrice() {
        return PRICE_PER_KWH;
    }

    @Override
    public void credit(double kWh) {
        if (kWh <= 0) return;
        battery.charge(kWh);  // Add purchased or extra energy
    }
 
    @Override
    public double purchaseEnergy(double kWh) {
        if (kWh <= 0) return 0.0;
        double cost = kWh * PRICE_PER_KWH;
        credit(kWh); // credit directly into the battery
        return cost;
    }
}
