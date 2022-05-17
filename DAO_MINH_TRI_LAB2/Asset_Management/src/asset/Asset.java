package asset;

/**
 *
 * @author tri
 */
public class Asset {
    protected String assetId, name, color;
    protected int price, quantity;
    protected double weight;

    public Asset(String assetId, String name, String color, int price, double weight, int quantity) {
        this.assetId = assetId;
        this.name = name;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
    }

    public String getAssetId() {
        return assetId;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public void showInformation() {
        System.out.printf("|%-8s|%-20s|%-10s|%-6d|%-7.1f|%-9d|\n", assetId, 
                name, color, price, weight, quantity);
    }
   
}
