package agriculture_simulator.core;

import java.util.Random;

public final class EnvironmentSimulator {
    private final Random rnd = new Random();

    public Environment newEnvironment() {
        double temp = 10 + rnd.nextDouble() * 40.0;       // 10..40 °C
        double soilMoisture = 20 + rnd.nextDouble() * 20.0; // 20..40 %
        return new Environment(temp, soilMoisture);
    }
}
