package database;

/**
 *
 * @author tri
 */
public class Motorbike extends Vehicle {
    protected double speed;
    protected boolean license;

    public Motorbike(String id, String name, String color, int price, String brand, double speed, boolean license) {
        super(id, name, color, price, brand);
        this.speed = speed;
        this.license = license;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean isLicense() {
        return license;
    }

    public void setLicense(boolean license) {
        this.license = license;
    }

@Override
    public void showInformation() {
        String msg;
        msg = String.format("|%-10s|%-15s|%-10s|%-13d|%-10s|%-6.2f%-6s|%12s", id, name, color, price, brand, speed, "km/h", String.valueOf(license).toUpperCase());
        System.out.println(msg);
    }
    
    public void makeSound() {
        System.out.println("Tin tin tin...");
    }
    
}
