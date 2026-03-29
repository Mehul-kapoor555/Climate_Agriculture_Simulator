package agriculture_simulator.crop;

import agriculture_simulator.core.EnvironmentView;

public interface Growable {
    void grow(EnvironmentView view);   // Grow given the current inside environment (read-only)
}  
