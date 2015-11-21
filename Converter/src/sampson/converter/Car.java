package sampson.converter;

public class Car {
    private Engine engine;
    private Wheel  wheel;
    
    public Engine getEngine() {
        return engine;
    }
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
    public Wheel getWheel() {
        return wheel;
    }
    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }
    
    @Override
    public String toString() {
        return String.format("Car Engine: %1$s; Car Wheel: %2$s", engine.toString(), wheel.toString());
    }
}
