package agriculture_simulator.exceptions;

public class EnergyShortageException extends Exception {
    private final double kWhNeeded;
    
    public EnergyShortageException(double kWhNeeded) {
        super(String.format("Need %.4f kWh", kWhNeeded));
        this.kWhNeeded = kWhNeeded;
    }
    public double getKWhNeeded() { return kWhNeeded; }
}
