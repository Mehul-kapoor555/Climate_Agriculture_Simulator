package agriculture_simulator.crop; 

import agriculture_simulator.core.EnvironmentView;
 
// Abstract Crop: encapsulates internal state (health, moisture, growthStage).

public abstract class Crop {
    private final String name;
    private double health;   // 0..100
    private double moisture; // 0..100 (crop-perceived moisture)
    private double growthStage; // integer stage/age

    protected Crop(String name) {
        this.name = name;
        this.health = 70.0;
        this.moisture = 60.0;
        this.growthStage = 0;
    }

    public String getName() {
        return name;
    }

    public double getHealth() {
        return health;
    }

    public double getMoisture() {
        return moisture;
    }

    public double getGrowthStage() {
        return growthStage;
    }

    protected final void changeHealth(double delta) {
        this.health = Math.max(0.0, Math.min(100.0, this.health + delta));
    }

    protected final void changeMoisture(double delta) {
        this.moisture = Math.max(0.0, Math.min(100.0, this.moisture + delta));
    }

    protected final void advanceGrowth(double delta) {
        if (delta <= 0) return;
        this.growthStage += delta;
    }

    public abstract String getStatus();
}
 
