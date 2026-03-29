package agriculture_simulator.crop;

import agriculture_simulator.core.EnvironmentView;

public class Rice extends Crop implements Growable {
    public Rice() {
        super("Rice");
    }

    @Override
    public void grow(EnvironmentView view) {
        double temp = view.getTemperature();
        double soil = view.getSoilMoisture();

        // Temperature effects
        if (temp >= 22.0 && temp <= 32.0) {
            changeHealth(+2.0);
            advanceGrowth(1.0);
        } else if (temp >= 18.0 && temp < 22.0) {
            changeHealth(+0.5);
            advanceGrowth(0.8);
        } else {
            changeHealth(-1.0);
        }

        // Moisture effects
        if (soil >= 60.0 && soil <= 80.0) {
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
