package database;

/**
 *
 * @author tri
 */
public class Car extends Vehicle {
    protected String type;
    protected int year;

    public Car(String id, String name, String color, int price, String brand, String type, int year) {
        super(id, name, color, price, brand);
        this.type = type;
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void showInformation() {
        String msg;
        msg = String.format("|%-10s|%-15s|%-10s|%-13d|%-10s|%-12s|%12d", id, name, color, price, brand, type, year);
        System.out.println(msg);
    }
}
