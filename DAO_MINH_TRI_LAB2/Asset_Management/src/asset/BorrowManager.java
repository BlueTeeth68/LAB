package asset;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import tool.Tool;

/**
 *
 * @author tri
 */
public class BorrowManager {

    private ArrayList<Borrow> b = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void readFile(String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner read = new Scanner(f);
            while (read.hasNextLine()) {
                String line = read.nextLine();
                if (line.trim() != "") {
                    String[] data = line.split(";");
                    String id, assetId, employeeId, date, time;
                    int quantity;

                    id = data[0].toString().toUpperCase();
                    assetId = data[1].toString().toUpperCase();
                    employeeId = data[2].toString().toUpperCase();
                    quantity = Integer.parseInt(data[3].toString());
                    date = data[4].toString();
                    time = data[5].toString();

                    b.add(new Borrow(id, assetId, employeeId, quantity, date, time));

                }
            }
            read.close();
        } catch (Exception e) {
            System.out.println("An error occured when read file!");
            e.printStackTrace();
        }
    }

    public void writeFile(String fileName) {
        try {
            File myFile = new File(fileName);
            myFile.createNewFile();
            FileWriter myWriter = new FileWriter(fileName);
            for (Borrow x : b) {
                String data = x.getId() + ";" + x.getAssetId() + ";" + x.getEmployeeId() + ";"
                        + x.getQuantity() + ";" + x.getDate() + ";" + x.getTime();
                myWriter.write(data + "\n");
            }
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occured when writing file!");
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return b.isEmpty();
    }

    public void showBorrowList() {
        if (isEmpty()) {
            System.out.println("Borrow list is empty.");
        } else {
            showBorrowTitle();
            for (Borrow x : b) {
                x.showInformation();
            }
        }
    }

    public String getDate() {
        String[] date = java.time.LocalDate.now().toString().split("-");

        String result = date[2] + "-" + date[1] + "-" + date[0];
        return result;
    }

    public String getTime() {
        String time = java.time.LocalTime.now().toString();
        String[] result = time.split("\\.");
        return result[0];
    }

    public void addABorrow(Request tmp) {
        String id, assetId, employeeId, date, time;
        int quantity;
        
        while(true) {
            id = Tool.getID("Input borrow ID: ", "ID must be form of Bxxx!", "^[b|B]\\d{3}$");
            if(checkID(id) == false) {
                break;
            } else {
                System.out.println("ID has been existed!");
            }
        }
        
        assetId = tmp.getAssetId();
        employeeId = tmp.getEmployeeId();
        quantity = tmp.getQuantity();
        date = getDate();
        time = getTime();
        
        b.add(new Borrow(id, assetId, employeeId, quantity, date, time));
        writeFile("D:\\DAO_MINH_TRI_LAB2\\borrow.dat");
    }

    public boolean checkID(String id) {
        if (isEmpty()) {
            return false;
        } else {
            for (Borrow x : b) {
                if(x.getId().equalsIgnoreCase(id)){
                    return true;
                }
            } return false;
        }
    }

    private void showBorrowTitle() {
        System.out.printf("|%-5s|%-8s|%-11s|%-9s|%-20s|\n", "bID", "AssetID", "EmployeeID",
                "Quantity", "requestDateTime");
        System.out.println("+-----+--------+-----------+---------+--------------------+");
    }
    
    public void removeAll() {
        b.removeAll(b);
    }

}
