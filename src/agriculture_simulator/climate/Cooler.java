package agriculture_simulator.climate;

import agriculture_simulator.core.EnvironmentController;
import agriculture_simulator.core.EnvironmentView;
import agriculture_simulator.energy.EnergyBroker;
import agriculture_simulator.exceptions.EnergyShortageException;

public class Cooler implements Device {
    private final EnergyBroker energyBroker;
    private final double targetTemperature;
    private final double tolerance;
    private final double kWhPerDegree;

    public Cooler(EnergyBroker energyBroker, double targetTemperature, double tolerance, double kWhPerDegree) {
        this.energyBroker = energyBroker;
        this.targetTemperature = targetTemperature;
        this.tolerance = Math.max(0.0, tolerance);
        this.kWhPerDegree = kWhPerDegree;
    }

    @Override
    public void adjust(EnvironmentController envCtrl, EnvironmentView view) throws EnergyShortageException {
        double current = view.getTemperature();
        double excessTemp = current - (targetTemperature + tolerance);

        if (excessTemp <= 0.0) return;

        double needed = excessTemp * kWhPerDegree;
        boolean ok = energyBroker.tryConsume(needed);
        if (ok) {
            envCtrl.decreaseTemperature(excessTemp);
        } else {
            // throw specific energy shortage exception
            throw new EnergyShortageException(needed);
        }

        System.out.printf("[Cooler] Temperature -%.2f (used %.2f kWh)%n",
                excessTemp, needed);
    }
}
