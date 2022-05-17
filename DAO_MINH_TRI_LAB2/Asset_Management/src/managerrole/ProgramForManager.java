package managerrole;

import asset.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import tool.Tool;
import userdata.Manager;
import java.util.ArrayList;
import userdata.Employee;
import userdata.User;

/**
 *
 * @author tri
 */
public class ProgramForManager {

    private ArrayList<Asset> m = new ArrayList<Asset>();
    private BorrowManager b = new BorrowManager();
    private RequestManager r = new RequestManager();

    private void getBorrowList() {
        b.readFile("D:\\DAO_MINH_TRI_LAB2\\borrow.dat");
    }

    private void saveBorrowList() {
        b.writeFile("D:\\DAO_MINH_TRI_LAB2\\borrow.dat");
    }

    private void getRequestList() {
        r.readFile("D:\\DAO_MINH_TRI_LAB2\\request.dat");
    }

    private void saveRequestList() {
        r.writeFile("D:\\DAO_MINH_TRI_LAB2\\request.dat");
    }

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
                    String data[] = line.split(";");
                    String assetId, name, color;
                    int price, quantity;
                    double weight;

                    assetId = data[0].toString().toUpperCase();
                    name = data[1].toString();
                    color = data[2].toString();
                    price = Integer.parseInt(data[3].toString());
                    weight = Double.parseDouble(data[4].toString());
                    quantity = Integer.parseInt(data[5].toString());

                    m.add(new Asset(assetId, name, color, price, weight, quantity));
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
            for (Asset x : m) {
                String data = x.getAssetId() + ";" + x.getName() + ";" + x.getColor()
                        + ";" + x.getPrice() + ";" + x.getWeight() + ";" + x.getQuantity();
                myWriter.write(data + "\n");
            }
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occured when writing file!");
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return m.isEmpty();
    }

    public boolean searchByName(String name) {
        name = name.toUpperCase();
        if (isEmpty()) {
            return false;
        } else {
//            ArrayList<Asset> tmp = new ArrayList<>();
            boolean isExist = false;
            for (int i = 0; i < m.size(); i++) {
                String[] check = m.get(i).getName().toUpperCase().split(name);
                if (check[0].equalsIgnoreCase(m.get(i).getName()) == false) {
                    if (isExist == false) {
                        System.out.println("");
                        printAssetTitle();
                        isExist = true;
                    }
                    m.get(i).showInformation();
                }
            }
            return isExist;
        }
    }

    public void searchByName() {
        System.out.println("");
        if (isEmpty()) {
            System.out.println("Asset list is empty!");
        } else {
            showAssetList();
            System.out.println("");
            String name = Tool.getString("Input name you want to search: ", "Name cann't "
                    + "be empty!");
            System.out.println("");
            boolean isExist = searchByName(name);
            if (!isExist) {
                System.out.println("Cann't find asset " + name + ".");
            }
        }
    }

//    private void sortDescendingByID(ArrayList<Asset> tmp) {
//        if (tmp.isEmpty()) {
//            return;
//        }
//        for (int i = 0; i < tmp.size(); i++) {
//            Asset a = tmp.get(i);
//            for (int j = tmp.size() - 1; j > i; j--) {
//                Asset b = tmp.get(j);
//                if (a.getAssetId().compareToIgnoreCase(b.getAssetId()) < 0) {
//                    Asset t = m.get(i);
//                    m.set(i, m.get(j));
//                    m.set(j, t);
//                }
//            }
//        }
//    }
    public void showAssetList() {
        if (isEmpty()) {
            return;
        } else {
            printAssetTitle();
            for (Asset x : m) {
                x.showInformation();
            }
        }
    }

    public void addANewAsset() {
        String rId, name, color;
        int price, quantity;
        double weight;

        boolean choice = true;
        do {
            while (true) {
                rId = Tool.getID("Input asset ID: ", "ID must be in form of Axxx! ", "^[A|a]\\d{3}$").toUpperCase();
                if (!checkID(rId)) {
                    break;
                } else {
                    System.out.println("ID has been existed!");
                }
            }

            name = Tool.getString("Input asset's name: ", "Name cann't be empty!");
            color = Tool.getString("Input asset's color: ", "Color cann't be empty!");
            price = Tool.getAnInteger("Input asset's price: ", "Price must be > 0 and <= 1000000",
                    1, 1000000);
            weight = Tool.getADouble("Input asset's weight: ", "Weight must be > 0 and <= 1000", 0.00001, 1000);
            quantity = Tool.getAnInteger("Input asset's quantity: ", "Quantity must be >= 0 and"
                    + " <= 1000", 0, 1000);
            m.add(new Asset(rId, name, color, price, weight, quantity));
            writeFile("D:\\DAO_MINH_TRI_LAB2\\asset.dat");
            System.out.println("Create asset successfully.\n");

            System.out.println("Do you want to continue to create another asset (y/n): ");
            choice = askYN("Input your choice: ", "Choice cannn't be empty!");
        } while (choice == true);

    }

    public int updateAnInteger(String inforMessage, String errorMessage, int upperBound, int lowerBound) {
        if (upperBound < lowerBound) {
            int tmp = upperBound;
            upperBound = lowerBound;
            lowerBound = tmp;
        }
        int n = 0;
        while (true) {
            try {
                String value;
                System.out.print(inforMessage);
                value = sc.nextLine().trim();
                if (value == "") {
                    return -1;
                } else {
                    n = Integer.parseInt(value);
                    if (n < lowerBound || n > upperBound) {
                        throw new Exception();
                    } else {
                        return n;
                    }
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    // Update một thuộc tính double
    public double updateADouble(String inforMessage, String errorMessage, double upperBound, double lowerBound) {
        if (upperBound < lowerBound) {
            double tmp = upperBound;
            upperBound = lowerBound;
            lowerBound = tmp;
        }
        double n;
        while (true) {
            try {
                String value;
                System.out.print(inforMessage);
                value = sc.nextLine().trim();
                if (value == "") {
                    return -1;
                } else {
                    n = Double.parseDouble(value);
                    if (n < lowerBound || n > upperBound) {
                        throw new Exception();
                    } else {
                        return n;
                    }
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    public void updateAsset() {

        if (isEmpty()) {
            System.out.println("Asset list is empty.");
        } else {
            String assetId;
            assetId = Tool.getString("Inputa asset ID you want to update: ", "ID cann't be empty!").toUpperCase();
            if (!checkID(assetId)) {
                System.out.println("\nUpdate false: Cann't find asset with ID " + assetId.toUpperCase());
            } else {
                int pos = getAssetByID(assetId);
                Asset tmp = m.get(pos);
                String name, color;
                int price, quantity;
                double weight;
                System.out.print("Input new name: ");
                name = sc.nextLine();
                if (name.trim() == "") {
                    name = tmp.getName();
                }
                System.out.print("Input new color: ");
                color = sc.nextLine();
                if (color.trim() == "") {
                    color = tmp.getColor();
                }

                price = updateAnInteger("Input new price: ", "Price must be > 0 and"
                        + " <= 1000000", 1, 1000000);
                if (price == -1) {
                    price = tmp.getPrice();
                }

                weight = updateADouble("Input new weight: ", "Weight must be > 0"
                        + " and <= 1000", 0.00001, 1000);
                if (weight == -1) {
                    weight = tmp.getWeight();
                }

                quantity = updateAnInteger("Input new quantity: ", "Quantity must be >= 0"
                        + "and <= 1000", 0, 1000);
                if (quantity == -1) {
                    quantity = tmp.getQuantity();
                }

                m.set(pos, new Asset(assetId, name, color, price, weight, quantity));
                System.out.println("\nUpdate successfully.");
                System.out.println("Here is the information of asset you've update: ");
                printAssetTitle();
                m.get(pos).showInformation();
                writeFile("D:\\DAO_MINH_TRI_LAB2\\asset.dat");
            }
        }
    }

    public int getAssetByID(String id) {
        if (isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < m.size(); i++) {
                if (m.get(i).getAssetId().equalsIgnoreCase(id)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public boolean checkID(String id) {
        if (isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < m.size(); i++) {
                if (m.get(i).getAssetId().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean askYN(String inforMessage, String errorMessage) {
        while (true) {
            String choice;
            choice = Tool.getString(inforMessage, errorMessage);
            if (choice.equalsIgnoreCase("y")) {
                return true;
            } else if (choice.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Invalid value!!!");
                System.out.println("Enter y/Y if yes.");
                System.out.println("Enter n/N if no.");
            }
        }
    }

    public void approveRequest() {
        r.removeAll();
        getRequestList();
        r.showRequestList();

        if (r.isEmpty()) {
            System.out.println("There is no request.");
        } else {
            boolean choice;
            System.out.println("Do you want to accept any request(y/n)? ");
            choice = askYN("Input your choice: ", "Choice cannn't be empty!");
            String rId;
            if (choice == true) {
                do {
                    while (true) {
                        rId = Tool.getID("Input request ID: ", "ID must be in form of "
                                + "Rxxx", "^[r|R]\\d{3}$").toUpperCase();
                        if (r.checkID(rId) == true) {
                            break;
                        } else {
                            System.out.println("Cann't find request ID!");
                        }
                    }
                    String assetId = r.getRequestByID(rId).getAssetId();
                    int quantity = m.get(getAssetByID(assetId)).getQuantity();
                    int rQuantity = r.getRequestByID(rId).getQuantity();

                    if (quantity <= 0) {
                        System.out.println("Error: There is no this asset type left in your company.");
                    } else if (quantity < rQuantity) {
                        System.out.println("Error: Request quantity is greater than current quantity.");
                    } else {
                        Request tmp = r.getRequestByID(rId);
                        b.removeAll();
                        getBorrowList();
                        b.addABorrow(tmp);
                        r.removeARequest(tmp.getId());

                        String newAssetId, newName, newColor;
                        int newPrice, newQuantity;
                        double weight;

                        Asset tmp1 = m.get(getAssetByID(assetId));
                        newAssetId = tmp1.getAssetId();
                        newName = tmp1.getName();
                        newColor = tmp1.getColor();
                        newPrice = tmp1.getPrice();
                        newQuantity = tmp1.getQuantity() - rQuantity;
                        weight = tmp1.getWeight();

                        int pos = getAssetByID(assetId);
                        m.set(pos, new Asset(assetId, newName, newColor, newPrice, weight, newQuantity));
                        writeFile("D:\\DAO_MINH_TRI_LAB2\\asset.dat");
                    }
                    System.out.println("Request has been approved.");
                    r.removeAll();
                    getRequestList();
                    r.showRequestList();

                    System.out.println("Do you want to continue to accept another request (y/n): ");
                    choice = askYN("Input your choice: ", "Choice cannn't be empty!");
                } while (choice == true);
            }
        }
    }

    public void showListOfBorrowAsset() {
        System.out.println("");
        b.removeAll();
        getBorrowList();
        b.showBorrowList();
    }
    
    public void sortAssetByQuantity() {
        if(isEmpty()){
            System.out.println("Asset list is empty.");
        } else {
            for (int i = 0; i < m.size(); i++) {
                for (int j = m.size() - 1; j > i; j--) {
                    if(m.get(i).getQuantity() > m.get(j).getQuantity()) {
                        Asset tmp = m.get(i);
                        m.set(i, m.get(j));
                        m.set(j, tmp);
                    }
                }
            }
            showAssetList();
        }
    }

    private void printAssetTitle() {
        System.out.printf("|%-8s|%-20s|%-10s|%-6s|%-7s|%-9s|\n", "AssetID", "Name",
                "Color", "Price", "Weight", "Quantity");
        System.out.println("+--------+--------------------+----------+------+-------+---------+");
    }
}
