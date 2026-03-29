package agriculture_simulator.energy;

import java.util.List;

import agriculture_simulator.weather.WeatherCondition;

// Minimal API devices use to consume/query energy. Implemented by EnergyManager.
public interface EnergyBroker {     
    
    boolean tryConsume(double amount);  
    void harvestAll(WeatherCondition outside);
    void credit(double kWh);          
    double purchaseEnergy(double kWh);

    double getAvailable();
    double getBatteryCapacity();
    double getEnergyPrice();
    List<EnergySource> getSources();
}
 