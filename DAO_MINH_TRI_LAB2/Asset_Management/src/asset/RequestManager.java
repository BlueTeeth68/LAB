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
public class RequestManager {

    private ArrayList<Request> r = new ArrayList<>();
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

                    r.add(new Request(id, assetId, employeeId, quantity, date, time));
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
            for (Request x : r) {
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
        return r.isEmpty();
    }

    public void showRequestList() {
        if (isEmpty()) {
            System.out.println("Request list is empty.");
        } else {
            showRequestTitle();
            for (Request x : r) {
                x.showInformation();
            }
        }
    }

    public int getRequestPosByID(String id) {
        if (isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < r.size(); i++) {
                if (r.get(i).getId().equalsIgnoreCase(id)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public Request getRequestByID(String id) {
        int pos = getRequestPosByID(id);
        if (pos == -1) {
            return null;
        } else {
            return r.get(pos);
        }
    }

    public boolean checkID(String ID) {
        if (isEmpty()) {
            return false;
        } else {
            for (Request x : r) {
                if (x.getId().equalsIgnoreCase(ID)) {
                    return true;
                }
            }
            return false;
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

    public void addARequest(String id, String assetId, String employeeId, int quantity) {
        
        String date, time;
        date = getDate();
        time = getTime();

        r.add(new Request(id, assetId, employeeId, quantity, date, time));
        writeFile("D:\\DAO_MINH_TRI_LAB2\\request.dat");
    }

    public void removeARequest(String id){
        int pos = getRequestPosByID(id);
        r.remove(r.get(pos));
        writeFile("D:\\DAO_MINH_TRI_LAB2\\request.dat");
    }
    
    private void showRequestTitle() {
        System.out.printf("|%-5s|%-8s|%-11s|%-9s|%-20s|\n", "rID", "AssetID", "EmployeeID",
                "Quantity", "requestDateTime");
        System.out.println("+-----+--------+-----------+---------+--------------------+");
    }

    
    public void removeAll() {
        r.removeAll(r);
    }
}
