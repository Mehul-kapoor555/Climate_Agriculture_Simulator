package agriculture_simulator.crop;

import agriculture_simulator.core.EnvironmentView;

public class SoyBean extends Crop implements Growable {
    public SoyBean() {
        super("SoyBean");
    }

    @Override
    public void grow(EnvironmentView view) {
        double temp = view.getTemperature();
        double soil = view.getSoilMoisture();

        if (temp >= 18.0 && temp <= 28.0) {
            changeHealth(+2.0);
            advanceGrowth(1.0);
        } else if (temp >= 14.0 && temp < 18.0) {
            changeHealth(+0.5);
        } else {
            changeHealth(-1.0);
        }

        if (soil >= 45.0 && soil <= 65.0) {
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
