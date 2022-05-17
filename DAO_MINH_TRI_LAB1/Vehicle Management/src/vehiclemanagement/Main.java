package vehiclemanagement;

import database.ShowRoom;
import ui.Menu;

/**
 *
 * @author tri
 */
public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.addNewOption("1. Add new vehicle");
        menu.addNewOption("2. Update vehicle");
        menu.addNewOption("3. Delete vehicle");
        menu.addNewOption("4. Search vehicle");
        menu.addNewOption("5. Show vehicle list");
        menu.addNewOption("6. Store to file");
        menu.addNewOption("7. Delete vehicle by name");
        menu.addNewOption("8. Quit");

        ShowRoom quan = new ShowRoom();
        quan.readFile("D:\\DAO_MINH_TRI_LAB1\\vehicles.txt");
        int choice;
        do {
            System.out.println("--------------Welcom to Vehicle Management Program--------------");
            menu.printMenu();
            System.out.println("----------------------------------------------------------------");
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    quan.addAVehicle();
                    break;
                case 2:
                    if (quan.isEmpty()) {
                        System.out.println("Your showroom doesn't have any vehicle to update.");
                    } else {
                        System.out.println("You are required to input vehicle's ID.");
                        quan.updateVehicle();
                    }
                    break;
                case 3:
                    if (quan.isEmpty()) {
                        System.out.println("Your showroom doesn't have any vehicle to delete.");
                    } else {
                        System.out.println("You are required to input vehicle's ID.");
                        quan.deleteAVehicle();
                    }
                    break;
                case 4:
                    quan.searchVehicle();
                    break;
                case 5:
                    quan.showAllVehicle();
                    break;
                case 6:
                    quan.writeFile("D:\\DAO_MINH_TRI_LAB1\\vehicles.txt");
                    break;
                case 7: 
                    quan.deleteVehicleByName();
                    break;
                case 8:
                    break;
            }
        } while (choice != 8);
    }
}
