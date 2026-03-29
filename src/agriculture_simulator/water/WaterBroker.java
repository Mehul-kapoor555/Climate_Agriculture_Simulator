package agriculture_simulator.water;

public interface WaterBroker {
    double getWaterLevel();  
    double getWaterPrice();  
    double getTankCapacity();  

    boolean consumeWater(double liters);
    void credit(double liters);          
    double purchaseWater(double liters);

    void firstTankFill();    // Called by Greenhouse
}
