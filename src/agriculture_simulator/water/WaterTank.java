package agriculture_simulator.water;

public class WaterTank {
    private double waterLevel;
    private final double tankCapacity;

    public WaterTank(double tankCapacity) {
        this.tankCapacity = tankCapacity;
        this.waterLevel = 0.0;
    } 
 
    public double getWaterLevel() {
        return waterLevel;
    }

    public double getTankCapacity() {
        return tankCapacity;
    }

    public void addWater(double liters) {
        if (liters <= 0.0) return;
        double freeSpace = tankCapacity - waterLevel;
        waterLevel += Math.min(freeSpace, liters);
    }

    public boolean tryConsume(double liters) {
        if (liters <= 0.0) return true;
        if (liters > waterLevel) return false;
        waterLevel -= liters;
        return true;
    }
    @Override
    public String toString() {
        return String.format("WaterTank{level=%.2f / %.2f L}", waterLevel, tankCapacity);
    }
}
