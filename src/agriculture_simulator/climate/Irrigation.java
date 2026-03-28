package agriculture_simulator.climate;

import agriculture_simulator.core.EnvironmentController;
import agriculture_simulator.core.EnvironmentView;
import agriculture_simulator.energy.EnergyBroker;
import agriculture_simulator.water.WaterBroker;
import agriculture_simulator.exceptions.EnergyShortageException;
import agriculture_simulator.exceptions.WaterShortageException;

public class Irrigation implements Device {
    private final EnergyBroker energyBroker;
    private final WaterBroker waterBroker;
    private final double targetSoilMoisture;  // percent 0..100
    private final double litersPerAction;     // liters applied each action
    private final double kWhPerAction;        // energy cost per irrigation action

    public Irrigation(EnergyBroker energyBroker, WaterBroker waterBroker,
                      double targetSoilMoisture, double litersPerAction, double kWhPerAction) {
        this.energyBroker = energyBroker;
        this.waterBroker = waterBroker;
        this.targetSoilMoisture = Math.max(0.0, Math.min(100.0, targetSoilMoisture));
        this.litersPerAction = Math.max(0.0, litersPerAction);
        this.kWhPerAction = Math.max(0.0, kWhPerAction);
    }

    @Override
    public void adjust(EnvironmentController envCtrl, EnvironmentView view)
            throws EnergyShortageException, WaterShortageException {

        double soilMoisture = view.getSoilMoisture();
        double deficit = targetSoilMoisture - soilMoisture;

        // Only irrigate if the soil is too dry
        if (deficit <= 0) return;

        // Step 1: compute how much water/energy needed
        double waterNeeded = deficit * 2.0;     // liters per % deficit
        double energyNeeded = deficit * 0.1;    // kWh per % deficit

        // Step 2: check energy availability
        boolean energyOk = energyBroker.tryConsume(energyNeeded);
        if (!energyOk) {
            throw new EnergyShortageException(energyNeeded);
        }

        // Step 3: check water availability
        boolean waterOk = waterBroker.consumeWater(waterNeeded);
        if (!waterOk) {
            throw new WaterShortageException(waterNeeded);
        }

        // Step 4: apply the effect to the environment
        double increase = deficit + soilMoisture; 
        envCtrl.increaseSoilMoisture(increase);

        System.out.printf("[Irrigation] Soil moisture +%.2f (used %.2f L water, %.2f kWh)%n",
                increase, waterNeeded, energyNeeded);
    }
}
 