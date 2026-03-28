package agriculture_simulator.core;

public class Environment implements EnvironmentController, EnvironmentView {
    private double temperature;   // °C
    private double soilMoisture;  // 0..100 %

    public Environment(double temperature, double soilMoisture) {
    this.temperature = temperature;
    this.soilMoisture = soilMoisture;
}

    // ---------------- EnvironmentView ----------------
    @Override public double getTemperature() { return temperature; }
    @Override public double getSoilMoisture(){ return soilMoisture; }

    // ---------------- EnvironmentController ----------------

    @Override
    public void increaseTemperature(double deltaC) {
        temperature += deltaC;
    }

    @Override
    public void decreaseTemperature(double deltaC) {
        temperature -= deltaC;
    }

    @Override
    public void increaseSoilMoisture(double deltaPercent) {
        soilMoisture += deltaPercent;
    }

    @Override
    public String toString() {
        return String.format("Environment [Temperature: %.2f°C, Soil Moisture: %.2f%%] %n",
                getTemperature(), getSoilMoisture());
    }
}
