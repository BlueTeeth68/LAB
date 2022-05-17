package asset;

/**
 *
 * @author tri
 */
public class Borrow extends BorrowAsset {

    public Borrow(String id, String assetId, String employeeId, int quantity, String date, String time) {
        super(id, assetId, employeeId, quantity, date, time);
    }
    
    public void showInformation() {
        System.out.printf("|%-5s|%-8s|%-11s|%-9d|%-10s %-9s|\n", id, assetId, employeeId,
                quantity, date, time);
    }
}
