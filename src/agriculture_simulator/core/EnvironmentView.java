package agriculture_simulator.core;

//EnvironmentView: read-only accessors to environment state exposed to crops and observers.

public interface EnvironmentView {
    double getTemperature();   
    double getSoilMoisture();  
}
