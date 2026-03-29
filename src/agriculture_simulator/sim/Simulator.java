package agriculture_simulator.sim;

import agriculture_simulator.setup.Factory;

public final class Simulator {
    public static void main(String[] args) {
        
        // Run the full console simulation: 3 days per greenhouse 
        Factory.runSimulation(3);
    }
}

/* If normal compilation does not work try this -- 
 *  javac -d out (Get-ChildItem -Recurse -Filter *.java | % FullName)
 * 
 * And then run using -- 
 *  java -cp out agriculture_simulator.sim.Simulator
 */
