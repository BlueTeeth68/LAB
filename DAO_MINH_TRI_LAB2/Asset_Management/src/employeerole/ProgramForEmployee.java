package employeerole;

import asset.*;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import tool.Tool;
import java.util.ArrayList;
import userdata.Employee;
import userdata.User;

/**
 *
 * @author tri
 */
public class ProgramForEmployee {

    private ArrayList<Asset> e = new ArrayList<Asset>();
    private RequestManager r = new RequestManager();

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

                    e.add(new Asset(assetId, name, color, price, weight, quantity));
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
            for (Asset x : e) {
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
        return e.isEmpty();
    }

    public boolean searchByName(String name) {
        name = name.toUpperCase();
        if (isEmpty()) {
            return false;
        } else {
            boolean isExist = false;
            ArrayList<Asset> tmp = new ArrayList<>();
            for (int i = 0; i < e.size(); i++) {
                String[] check = e.get(i).getName().toUpperCase().split(name);
                if (check[0].equalsIgnoreCase(e.get(i).getName()) == false) {
                    if (isExist == false) {
                        System.out.println("");
                        printAssetTitle();
                        isExist = true;
                    }
                    e.get(i).showInformation();
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
//                    Asset t = a;
//                    a = b;
//                    b = t;
//                }
//            }
//        }
//    }
    public void showAssetList() {
        if (isEmpty()) {
            return;
        } else {
            printAssetTitle();
            for (Asset x : e) {
                x.showInformation();
            }
        }
    }

    public void sendRequest(String employeeId) {
        String rId, assetId;
        int quantity;

        getRequestList();
        while (true) {
            rId = Tool.getID("Input request ID: ", "ID must be form of Rxxx!", "^[r|R]\\d{3}$");
            if (r.checkID(rId) == false) {
                break;
            } else {
                System.out.println("ID has been existed!");
            }
        }

        while (true) {
            assetId = Tool.getID("Input asset ID: ", "ID must be form of Axxx!", "^[A|a]\\d{3}$");
            if (checkID(assetId) == true) {
                break;
            } else {
                System.out.println("Asset ID doesn't exist!");
            }
        }

        quantity = Tool.getAnInteger("Input quantity you want to borrow: ", "Quantity must be > 0 and "
                + "<= 1000", 1, 1000);

        r.addARequest(rId, assetId, employeeId, quantity);
        System.out.println("Send request successfully.");

    }

    public boolean checkID(String id) {
        if (isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < e.size(); i++) {
                if (e.get(i).getAssetId().equalsIgnoreCase(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void printAssetTitle() {
        System.out.printf("|%-8s|%-20s|%-10s|%-6s|%-7s|%-9s|\n", "AssetID", "Name",
                "Color", "Price", "Weight", "Quantity");
        System.out.println("+--------+--------------------+----------+------+-------+---------+");
    }
}
