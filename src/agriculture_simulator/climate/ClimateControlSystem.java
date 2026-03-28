package agriculture_simulator.climate;

import agriculture_simulator.core.EnvironmentController;
import agriculture_simulator.core.EnvironmentView;
import agriculture_simulator.climate.Device;
import agriculture_simulator.energy.EnergyBroker;
import agriculture_simulator.water.WaterBroker;
import agriculture_simulator.exceptions.EnergyShortageException;
import agriculture_simulator.exceptions.WaterShortageException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ClimateControlSystem coordinates devices and handles resource shortages by
 * requesting purchases from the brokers. It retries the device once after purchase.
 */
public class ClimateControlSystem {
    private final List<Device> devices;
    private final EnergyBroker energyBroker;
    private final WaterBroker waterBroker;

    public ClimateControlSystem(List<Device> devices,
                                EnergyBroker energyBroker,
                                WaterBroker waterBroker) {
        this.devices = Collections.unmodifiableList(new ArrayList<>(devices));
        this.energyBroker = energyBroker;
        this.waterBroker = waterBroker;
    }
    
    /**
     * Adjust all devices. If a device throws a shortage exception, buy the needed resource,
     * credit the broker (purchase methods should perform crediting) and retry the device once.
     */

    public void adjustAll(EnvironmentController envCtrl, EnvironmentView view) {
        for (Device d : devices) {
            try {
                d.adjust(envCtrl, view);
            } catch (EnergyShortageException ese) {
                double needed = ese.getKWhNeeded();
                if (energyBroker != null) {
                    double cost = energyBroker.purchaseEnergy(needed);
                    System.out.printf("[ClimateControlSystem] purchased %.3f kWh for %.2f EUR for %s%n",
                            needed, cost, d.getClass().getSimpleName());
                } else {
                    System.err.printf("[ClimateControlSystem] energy shortage %.3f kWh and no broker%n", needed);
                }
                // retry once
                try { d.adjust(envCtrl, view); } catch (Exception ex) {
                    System.err.println("[ClimateControlSystem] device failed after purchase: " + ex.getMessage());
                }
            } catch (WaterShortageException wse) {
                double needed = wse.getLitersNeeded();
                if (waterBroker != null) {
                    double cost = waterBroker.purchaseWater(needed);
                    System.out.printf("[ClimateControlSystem] purchased %.3f L water for %.2f EUR for %s%n",
                            needed, cost, d.getClass().getSimpleName());
                } else {
                    System.err.printf("[ClimateControlSystem] water shortage %.3f L and no broker%n", needed);
                }
                // retry once
                try { d.adjust(envCtrl, view); } catch (Exception ex) {
                    System.err.println("[ClimateControlSystem] device failed after water purchase: " + ex.getMessage());
                }
            } catch (Exception ex) {
                System.err.println("[ClimateControlSystem] device error: " + ex.getMessage());
            }
        }
    }
}
