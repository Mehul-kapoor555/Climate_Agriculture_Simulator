package agriculture_simulator.energy;
 
public class Battery {
    private double storedKWh;
    private final double capacityKWh;

    public Battery(double capacityKWh) {
        this.capacityKWh = capacityKWh;
        this.storedKWh = 0.0;
    }

    double charge(double producedKWh) {
    double chargedAmount = Math.min(producedKWh, capacityKWh - storedKWh);
    storedKWh += chargedAmount;
    return chargedAmount;  
    }

    double discharge(double requestedKWh) {
    double dischargedAmount = Math.min(requestedKWh, storedKWh);
    storedKWh -= dischargedAmount;
    return dischargedAmount;  
    }

    public double getStored() { 
        return storedKWh; 
    }

    public double getCapacity() { 
        return capacityKWh; 
    }
}
