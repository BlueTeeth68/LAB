package asset;

/**
 *
 * @author tri
 */
public abstract class BorrowAsset implements Comparable<BorrowAsset>{
    protected String id, assetId, employeeId, date, time;
    protected int quantity;

    public BorrowAsset(String id, String assetId, String employeeId, int quantity, String date, String time) {
        this.id = id;
        this.assetId = assetId;
        this.employeeId = employeeId;
        this.date = date;
        this.time = time;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public abstract void showInformation();
    
    public int compareTo(BorrowAsset that) {
        return this.id.compareToIgnoreCase(that.id);
    }
}
