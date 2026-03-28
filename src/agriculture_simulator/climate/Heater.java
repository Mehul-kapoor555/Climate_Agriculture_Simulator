package agriculture_simulator.climate;

import agriculture_simulator.core.EnvironmentController;
import agriculture_simulator.core.EnvironmentView;
import agriculture_simulator.energy.EnergyBroker;
import agriculture_simulator.exceptions.EnergyShortageException;

public class Heater implements Device {
    private final EnergyBroker energyBroker;
    private final double targetTemperature;
    private final double tolerance;
    private final double kWhPerDegree;

    public Heater(EnergyBroker energyBroker, double targetTemperature, double tolerance, double kWhPerDegree) {
        this.energyBroker = energyBroker;
        this.targetTemperature = targetTemperature;
        this.tolerance = Math.max(0.0, tolerance);
        this.kWhPerDegree = Math.max(0.0, kWhPerDegree);
    }

    @Override
    public void adjust(EnvironmentController envCtrl, EnvironmentView view) throws EnergyShortageException {
        double current = view.getTemperature();

        if (current >= (targetTemperature - tolerance)) return;

        double delta = targetTemperature - current;
        double needed = delta * kWhPerDegree;

        boolean ok = energyBroker.tryConsume(needed);
        if (!ok) {
            // not enough energy now — inform the climate control system
            throw new EnergyShortageException(needed);
        }

        // energy consumed successfully -> apply temperature change
        envCtrl.increaseTemperature(delta);

        System.out.printf("[Heater] Temperature +%.2f (used %.2f kWh)%n",
                delta, needed);
    }
}
