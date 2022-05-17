package database;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.IOException;
import tool.InputTool;
import ui.Menu;

/**
 *
 * @author tri
 */
public class ShowRoom {

    private ArrayList<Vehicle> showRoom = new ArrayList();

    private Scanner sc = new Scanner(System.in);

    public void writeFile(String fileName) {

        try {
            File myFile = new File(fileName);
            myFile.createNewFile();
            FileWriter myWriter = new FileWriter(fileName);
            for (Vehicle x : showRoom) {
                String data;
                if (x.getId().matches("^[C|c][A|a]\\d{5}$")) {
                    data = x.getId() + " " + x.getName() + " " + x.getColor() + " "
                            + x.getPrice() + " " + x.getBrand() + " "
                            + ((Car) x).getType() + " " + ((Car) x).getYear();
                    myWriter.write(data + "\n");
                } else if (x.getId().matches("^[M|m][B|b]\\d{5}$")) {
                    data = x.getId() + " " + x.getName() + " " + x.getColor() + " "
                            + x.getPrice() + " " + x.getBrand() + " "
                            + ((Motorbike) x).getSpeed() + " " + ((Motorbike) x).isLicense();
                    myWriter.write(data + "\n");
                }
            }
            myWriter.close();
            System.out.println("Store data successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred when saving file!");
            e.printStackTrace();
        }
    }

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
                    String data[] = line.split("\\s");
                    String id, name, color, brand, type;
                    int year;
                    int price;
                    double speed;
                    boolean license;
                    id = data[0].toString();
                    name = data[1].toString();
                    color = data[2].toString();
                    price = Integer.parseInt(data[3].toString());
                    brand = data[4].toString();
                    if (id.matches("^[C|c][A|a]\\d{5}$")) {
                        type = data[5].toString();
                        year = Integer.parseInt(data[6].toString());
                        showRoom.add(new Car(id, name, color, price, brand, type, year));
                    } else if (id.matches("^[M|m][B|b]\\d{5}$")) {
                        speed = Double.parseDouble(data[5].toString());
                        license = Boolean.parseBoolean(data[6].toString());
                        showRoom.add(new Motorbike(id, name, color, price, brand, speed, license));
                    }
                }
            }
            read.close();
        } catch (Exception e) {
            System.out.println("An error occured when read file!");
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return showRoom.isEmpty();
    }

    //kiểm tra ID có trùng hay không
    public boolean checkID(Vehicle A) {
        String id = A.getId();
        return checkID(id);
    }

    //kiểm tra ID có trùng hay không
    public boolean checkID(String id) {
        if (isEmpty()) {
            return false;
        } else {
            for (int i = 0; i < showRoom.size(); i++) {
                if (showRoom.get(i).getId().equalsIgnoreCase(id)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void addACar() {
        String id, name, color, brand, type;
        int price;
        int year;

        while (true) {
            id = InputTool.getID("Input Car's ID (CAXXXXX): ", "ID must be in the form of "
                    + "CAXXXXX", "^[C][A]\\d{5}$");
            if (!checkID(id)) {
                break;
            } else {
                System.out.println("ID has been existed!!!");
            }
        }

        name = InputTool.getString("Input name: ", "Name cann't be empty!!!").toUpperCase().trim();
        color = InputTool.getString("Input color: ", "Color cann't be empty!!!").toUpperCase().trim();
        price = InputTool.getAnInteger("Input price ($): ", "Price must be a natural number "
                + "between 0 and 200.000.000$", 0, 200000000);
        brand = InputTool.getString("Input brand: ", "Brand cann't be empty!!!").toUpperCase().trim();
        type = InputTool.getString("Input type: ", "Type cann't be empty!!!").toUpperCase().trim();
        year = InputTool.getAnInteger("Input year of manufacture: ", "Year must be "
                + "a number between 1885 and 2022", 1885, 2022);

        showRoom.add(new Car(id, name, color, price, brand, type, year));
        System.out.println("Add car successfully!");
    }

    public void addMotorbike() {
        String id, name, color, brand;
        double speed;
        int price;
        boolean license = false;

        while (true) {
            id = InputTool.getID("Input motorbike's ID (MBXXXXX): ", "ID must be in the form of "
                    + "MBXXXXX", "^[M][B]\\d{5}$");
            if (!checkID(id)) {
                break;
            } else {
                System.out.println("ID has been existed!!!");
            }
        }

        name = InputTool.getString("Input name: ", "Name cann't be empty!!!").toUpperCase().trim();
        color = InputTool.getString("Input color: ", "Color cann't be empty!!!").toUpperCase().trim();
        price = InputTool.getAnInteger("Input price ($): ", "Price must be a natural number "
                + "between 0 and 100.000.000$", 0, 100000000);
        brand = InputTool.getString("Input brand: ", "Brand cann't be empty!!!").toUpperCase().trim();
        speed = InputTool.getADouble("Input speed: ", "Speed must be greater than 0"
                + " and less than 500", 0.00001, 500);

        license = askYN("Does motor require license or not? (y/n): ", "License cann't be empty!");

        showRoom.add(new Motorbike(id, name, color, price, brand, speed, license));
        System.out.println("Add motobike successfully.");
    }

    public void updateVehicle() {
        String id;
        boolean isCar = false, isMotor = false;
        id = InputTool.getString("Input ID: ", "ID cann't be empty.").toUpperCase();
        int pos = searchVehicleByID(id);
        if (pos == -1) {
            System.out.println("Update fail: vehicle doesn't exist!!!");
        } else {
            if (id.matches("^[C][A]\\d{5}$")) {
                isCar = true;
            } else if (id.matches("^[M][B]\\d{5}$")) {
                isMotor = true;
            }
            Vehicle tmp = showRoom.get(pos);
            System.out.println("Here is vehicle you want to update: ");
            showVehicleTitle();
            tmp.showInformation();
            String newId, name, color, brand;
            int price;

            while (true) {
                System.out.print("Input new ID (CAXXXXX/MBXXXXX): ");
                newId = sc.nextLine().trim().toUpperCase();
                if (newId == "") {
                    newId = tmp.getId();
                    break;
                } else if (newId.matches("^[C][A]\\d{5}$") && isCar == true) {
                    if (checkID(newId)) {
                        System.out.println("ID has been existed!!!");
                    } else {
                        tmp.setId(newId);
                        break;
                    }
                } else if (newId.matches("^[M][B]\\d{5}$") && isMotor == true) {
                    if (checkID(newId)) {
                        System.out.println("ID has been existed!!!");
                    } else {
                        tmp.setId(newId);
                        break;
                    }
                } else {
                    System.out.println("Invalid ID!!! ID must be in the same format with old ID");
                }
            }

            System.out.print("Input new name: ");
            name = sc.nextLine().trim().toUpperCase();
            if (name != "") {
                tmp.setName(name);
            }
            System.out.print("Input new color: ");
            color = sc.nextLine().trim().toUpperCase();
            if (color != "") {
                tmp.setColor(color);
            }
            System.out.print("Input new brand: ");
            brand = sc.nextLine().trim().toUpperCase();
            if (brand != "") {
                tmp.setBrand(brand);
            }

            price = updateAnInteger("Input new price ($): ", "Price must be a positive number "
                    + "that less than 200.000.000", 0, 200000000);
            if (price != -1) {
                tmp.setPrice(price);
            }

            if (newId.matches("^[C][A]\\d{5}$")) {
                String type;
                int year;
                Car car = (Car) tmp;

                System.out.print("Input type of car: ");
                type = sc.nextLine().trim().toUpperCase();
                if (type != "") {
                    car.setType(type);
                }

                year = updateAnInteger("Input year of manifaction: ", "Year must be "
                        + "an integer between 1885 and 2022", 1885, 2022);
                if (year != -1) {
                    car.setYear(year);
                }

            } else if (newId.matches("^[M][B]\\d{5}$")) {
                Motorbike mb = (Motorbike) tmp;
                double speed;

                speed = updateADouble("Input new motor's speed: ", "Speed must be "
                        + "greater than 0 and less than 500", 0.000001, 500);
                if (speed != -1) {
                    mb.setSpeed(speed);
                }

                while (true) {
                    String choice;
                    System.out.print("Does motor require license or not? (y/n): ");
                    choice = sc.nextLine().trim();
                    if (choice.equalsIgnoreCase("y")) {
                        mb.setLicense(true);
                        break;
                    } else if (choice.equalsIgnoreCase("n")) {
                        mb.setLicense(false);
                        break;
                    } else if (choice == "") {
                        break;
                    } else {
                        System.out.println("Invalid value!!!");
                        System.out.println("Enter y/Y if yes");
                        System.out.println("Enter n/N if no");
                        System.out.println("Skip if you donn't change license.");
                    }
                }
            }
            System.out.println("Here is your vehicle after update: ");
            showVehicleTitle();
            tmp.showInformation();
        }
    }

    //Update một thuộc tính Integer
    public int updateAnInteger(String inforMessage, String errorMessage, int upperBound, int lowerBound) {
        if (upperBound < lowerBound) {
            int tmp = upperBound;
            upperBound = lowerBound;
            lowerBound = tmp;
        }
        int n;
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

    //Sắp xếp vehicle trong showRoom theo giá giảm dần
    public void sortDescendingByPrice() {
        if (!isEmpty() && showRoom.size() >= 2) {
            for (int i = 0; i < showRoom.size() - 1; i++) {
                for (int j = showRoom.size() - 1; j > i; j--) {
                    if (showRoom.get(i).getPrice() < showRoom.get(j).getPrice()) {
                        Vehicle tmp;
                        tmp = showRoom.get(i);
                        showRoom.set(i, showRoom.get(j));
                        showRoom.set(j, tmp);
                    }
                }
            }
        } else {
            System.out.println("Error.");
        }
    }

    //Show danh sách vehicle theo thứ tự giảm dần theo giá
    // Nếu vehicle là motor thì gọi hàm makeSound()
    public void showAllDescendingByPrice() {
        if (isEmpty()) {
            System.out.println("There is no vehicle in your showroom.");
        } else {
            sortDescendingByPrice();
            showVehicleTitle();
            for (int i = 0; i < showRoom.size(); i++) {
                showRoom.get(i).showInformation();
                if (showRoom.get(i).getId().matches("^[M][B]\\d{5}$")) {
                    ((Motorbike) showRoom.get(i)).makeSound();
                }
            }
        }
    }

    public void showAll() {                                                 //show All
        if (isEmpty()) {
            System.out.println("There is no vehicle in your showroom.");
        } else {
            showVehicleTitle();
            for (int i = 0; i < showRoom.size(); i++) {
                showRoom.get(i).showInformation();
            }
        }
    }

    //Search vehicle bằng ID, trả về vị trí của vehicle
    public int searchVehicleByID(String id) {
        for (int i = 0; i < showRoom.size(); i++) {
            if (showRoom.get(i).getId().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    //Tìm kiếm vehicle bằng ID, in thông tin vehicle ra màn hình
    public void searchByID(String id) {
        int i = searchVehicleByID(id);
        if (i == -1) {
            System.out.println("Vehicle doesn't exist.");
        } else {
            showVehicleTitle();
            showRoom.get(i).showInformation();
        }
    }

    //In ra danh sách các vehicle có name trùng với name search
    public void searchByName(String name) {
        boolean isExist = false;
        for (int i = 0; i < showRoom.size(); i++) {
            if (showRoom.get(i).getName().equalsIgnoreCase(name)) {
                if (isExist == false) {
                    showVehicleTitle();
                    isExist = true;
                }
                showRoom.get(i).showInformation();
            }
        }
        if (isExist == false) {
            System.out.println("Not found Vehicle " + name);
        }
    }

    public void deleteVehicleByName() {
        String name;
        name = InputTool.getString("Input name you want to delete: ", "Name cann't be empty!!!");
        boolean isExist = false;

        for (int i = 0; i < showRoom.size(); i++) {
            if (showRoom.get(i).getName().equalsIgnoreCase(name)) {
                if (isExist == false) {
                    System.out.println("Here is vehicle you want to delete: ");
                    showVehicleTitle();
                    isExist = true;
                }
                showRoom.get(i).showInformation();
            }
        }
        if (isExist == true) {
            boolean choice = askYN("Are you sure to delete these vehicle? (Y/N): ", "Choice cann't be empty!!!");
            if (choice == true) {
                for (int i = 0; i < showRoom.size(); i++) {
                    if (showRoom.get(i).getName().equalsIgnoreCase(name)) {
                        showRoom.remove(i);
                    }
                }
                System.out.println("Delete vehicle successfully.");
            } else {
                System.out.println("Delete vehicle fail.");
            }
        }
        if (isExist == false) {
            System.out.println("Delete fail: Vehicle " + name + " doesn't exist.");
        }

    }

    // Xóa một vehicle, in thông tin vehicle trước khi xóa và in thông báo 
    // việc xóa đã thành công hay chưa ra màn hình
    public void deleteAVehicle() {
        String id;
        boolean choice;
        id = InputTool.getString("Input ID of vehicle you want to delete: ",
                "ID cann't be empty!!!");
        int pos;
        pos = searchVehicleByID(id);
        if (pos >= 0) {
            System.out.println("Here is vehicle you want to delete: ");
            showVehicleTitle();
            showRoom.get(pos).showInformation();
            choice = askYN("Are you sure to delete this vehicle? (Y/N): ", "Choice cann't be empty!!!");
            if (choice == true) {
                showRoom.remove(pos);
                System.out.println("Delete vehicle " + id + " successfully.");
            } else {
                System.out.println("Delete vehicle fail.");
            }
        } else {
            System.out.println("Delete vehicle fail: Vehicle doesn't exist!");
        }
    }

    public void addAVehicle() {
        Menu menu = new Menu();
        menu.addNewOption("1. Add a car");
        menu.addNewOption("2. Add a motorbike");
        menu.addNewOption("3. Return main menu");
        int choice;
        boolean ask = true;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            if (choice == 1) {
                System.out.println("You are preparing to add a car to showroom");
                addACar();
                System.out.printf("\nDo you want to continue adding vehicle (Y/N)?\n");
                ask = askYN("Enter your choice: ", "Choice cann't be empty!!!");
            } else if (choice == 2) {
                System.out.println("You are preparing to add a motorbike to showroom");
                addMotorbike();
                System.out.printf("\nDo you want to continue adding vehicle (Y/N)?\n");
                ask = askYN("Enter your choice: ", "Choice cann't be empty!!!");
            } else {
                ask = false;
            }

        } while (ask == true);

    }

    public void searchVehicle() {
        if (isEmpty()) {
            System.out.println("Your showroom doesn't have any vehicle.");
        } else {
            Menu menu = new Menu();
            menu.addNewOption("1. Search by name");
            menu.addNewOption("2. Search by ID");
            menu.addNewOption("3. Return main menu");
            int choice;
            menu.printMenu();
            choice = menu.getChoice();
            if (choice == 1) {
                String name = InputTool.getString("Input name you want to search: ", "Name cann't be empty.");
                searchByName(name);
            } else if (choice == 2) {
                String id = InputTool.getString("Input ID you want to search: ", "ID cann't be empty.");
                searchByID(id);
            }
        }
    }

    public void showAllVehicle() {
        Menu menu = new Menu();
        menu.addNewOption("1. Show all");
        menu.addNewOption("2. Show all descending by price");
        menu.addNewOption("3. Return main menu");
        int choice;
        menu.printMenu();
        choice = menu.getChoice();
        if (choice == 1) {
            showAll();
        } else if (choice == 2) {
            showAllDescendingByPrice();
        }
    }

    //Xử lý các lựa chọn Yes/No, trả về kiểu boolean
    private boolean askYN(String inforMessage, String errorMessage) {
        while (true) {
            String choice;
            choice = InputTool.getString(inforMessage, errorMessage);
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

    private void showVehicleTitle() {
        String msg;
        msg = String.format("|%-10s|%-15s|%-10s|%-13s|%-10s|%-12s|%12s", "ID", "NAME", "COLOR", "PRICE",
                "BRAND", "SPEED/TYPE", "YEAR/LICENSE");
        System.out.println(msg);
    }

}
