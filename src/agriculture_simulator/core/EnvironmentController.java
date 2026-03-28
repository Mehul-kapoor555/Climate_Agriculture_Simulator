package agriculture_simulator.core;

public interface EnvironmentController {
    void increaseTemperature(double deltaC); 
    void decreaseTemperature(double deltaC); 

    void increaseSoilMoisture(double deltaPercent); 
}
