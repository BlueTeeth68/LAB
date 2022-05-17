package database;

/**
 *
 * @author tri
 */
public abstract class Vehicle implements Comparable<Vehicle> {

    String id, name, color, brand;
    int price;

    public Vehicle(String id, String name, String color, int price, String brand) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.brand = brand;
        this.price = price;
    }

    public abstract void showInformation();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public int compareTo(Vehicle that) {
        return this.id.compareToIgnoreCase(that.id);
    }
    //so sánh id của Vehicle, không quan tâm là Car hay Motobike

}
