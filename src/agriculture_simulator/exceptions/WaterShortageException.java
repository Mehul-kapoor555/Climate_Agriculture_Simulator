package agriculture_simulator.exceptions;

public class WaterShortageException extends Exception {
    private final double litersNeeded;
    
    public WaterShortageException(double litersNeeded) {
        super(String.format("Need %.3f L", litersNeeded));
        this.litersNeeded = litersNeeded;
    }
    public double getLitersNeeded() { return litersNeeded; }
}
