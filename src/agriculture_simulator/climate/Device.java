package agriculture_simulator.climate;

import agriculture_simulator.core.EnvironmentController;
import agriculture_simulator.core.EnvironmentView;
import agriculture_simulator.exceptions.EnergyShortageException;
import agriculture_simulator.exceptions.WaterShortageException;

public interface Device {
    void adjust(EnvironmentController envCtrl, EnvironmentView view)
            throws EnergyShortageException, WaterShortageException;
}
