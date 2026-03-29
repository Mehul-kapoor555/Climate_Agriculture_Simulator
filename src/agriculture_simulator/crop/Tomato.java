package agriculture_simulator.crop;

import agriculture_simulator.core.EnvironmentView;

public class Tomato extends Crop implements Growable {
    public Tomato() {
        super("Tomato");
    }

    @Override
    public void grow(EnvironmentView view) {
        double temp = view.getTemperature();
        double soil = view.getSoilMoisture();

        // Temperature effects (simple)
        if (temp >= 20.0 && temp <= 30.0) {
            changeHealth(+2.0);
            advanceGrowth(1.0);
        } else if (temp >= 15.0 && temp < 20.0) {
            changeHealth(+0.5);
            advanceGrowth(0.7);
        } else {
            changeHealth(-1.0);
        }

        // Moisture effects based on soil moisture
        if (soil >= 50.0 && soil <= 70.0) {
            changeMoisture(+1.0);
        } else {
            changeMoisture(-1.0);
        }
    }
    
    @Override
    public String getStatus() {
        return String.format("%s | Health: %.1f%% | Moisture: %.1f%% | Stage: %.1f",
                getName(), getHealth(), getMoisture(), getGrowthStage());
    }
}
