package agriculture_simulator.water;

import agriculture_simulator.energy.EnergyBroker;
import agriculture_simulator.exceptions.EnergyShortageException;

public class WaterManager implements WaterBroker {
    private final WaterTank tank;
    private final EnergyBroker energyBroker;
    private static final double PRICE_PER_LITER  = 2.0;      // €/L
    private final double initialFillFraction; // e.g., 0.5 => fill to 50% capacity
    private final double energyPerLiter;      // kWh required per liter pumped

    public WaterManager(WaterTank tank, EnergyBroker energyBroker) {
        this(tank, energyBroker, 0.3, 0.5);
    }

    public WaterManager(WaterTank tank, EnergyBroker energyBroker,
                        double initialFillFraction, double energyPerLiter) {
        this.tank = tank;
        this.energyBroker = energyBroker;
        this.initialFillFraction = Math.max(0.0, Math.min(1.0, initialFillFraction));
        this.energyPerLiter = Math.max(0.0, energyPerLiter);
    }

    @Override
    public double getWaterLevel() { return tank.getWaterLevel(); }

    @Override
    public double getTankCapacity() { return tank.getTankCapacity(); }
    
    @Override
    public double getWaterPrice() { return PRICE_PER_LITER; }

    @Override
    public boolean consumeWater(double liters) { return tank.tryConsume(liters); }

    @Override
    public void credit(double liters) {
        if (liters > 0.0) tank.addWater(liters);
    }

    @Override
    public double purchaseWater(double liters) {
        if (liters <= 0.0) return 0.0;
        double cost = liters * PRICE_PER_LITER ;
        credit(liters);
        return cost;
    }

    @Override
    public void firstTankFill() {
        double capacity = getTankCapacity();
        if (capacity <= 0.0) return;

        double desiredLevel = capacity * initialFillFraction;
        double current = getWaterLevel();

        if (current >= desiredLevel) {
            System.out.printf("[WaterManager] Tank already at %.2f / %.2f L. %n%n",
                    getWaterLevel(), capacity);
            return;
        } 
        
        else {
            double toAdd = desiredLevel - current;
            double energyNeeded = toAdd * energyPerLiter;

            try {
                boolean enoughEnergy = energyBroker.tryConsume(energyNeeded);
                if (!enoughEnergy) {
                    throw new EnergyShortageException(energyNeeded);
                }

                // Energy available → pump the water normally
                credit(toAdd);
                System.out.printf("[WaterManager] Pumped %.2f L using %.2f kWh (tank now %.1f%% full)%n%n",
                        toAdd, energyNeeded, (getWaterLevel() / capacity) * 100.0);

            } catch (EnergyShortageException ex) {
                // Not enough energy → directly purchase water
                double cost = purchaseWater(toAdd);
                System.out.printf("[WaterManager] Not enough energy (%.2f kWh needed). "
                                + "Purchased %.2f L of water from grid for %.2f Euros.%n"
                                + "[Water Tank] Tank at %.2f / %.2f L. %n%n",
                        ex.getKWhNeeded(), toAdd, cost, getWaterLevel(), capacity);
            }
        }
    }

    @Override
    public String toString() {
        return String.format("WaterManager {Water Level = %.2f /% .2f L}", getWaterLevel(), getTankCapacity());
    }
}
