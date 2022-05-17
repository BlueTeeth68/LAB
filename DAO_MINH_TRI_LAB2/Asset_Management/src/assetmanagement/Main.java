package assetmanagement;

import employeerole.ProgramForEmployee;
import java.util.Scanner;
import managerrole.ProgramForManager;
import ui.Menu;
import userdata.Login;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Login l = new Login();
        l.readFile("D:\\DAO_MINH_TRI_LAB2\\employee.dat");
        System.out.println("LOGIN: ");
        int role = l.logIn();

        if (role == 1) {
            Menu menu = new Menu();
            menu.addNewOption("1. Search Asset By Name.");
            menu.addNewOption("2. Create New Asset");
            menu.addNewOption("3. Update Asset Information");
            menu.addNewOption("4. Approve Request");
            menu.addNewOption("5. Show List Of Borrow Asset");
            menu.addNewOption("6. Sort Asset Accending By Quantity.");
            menu.addNewOption("7. Quit");

            ProgramForManager ma = new ProgramForManager();
            ma.readFile("D:\\DAO_MINH_TRI_LAB2\\asset.dat");

            int choice;
            do {
                System.out.println("");
                System.out.println("--------------Welcome to Asset Management Program---------------");
                menu.printMenu();
                System.out.println("----------------------------------------------------------------");
                choice = menu.getChoice();
                switch (choice) {

                    case 1:
                        ma.searchByName();
                        break;
                    case 2:
                        ma.addANewAsset();
                        break;
                    case 3:
                        ma.updateAsset();
                        break;
                    case 4:
                        ma.approveRequest();
                        break;
                    case 5:
                        ma.showListOfBorrowAsset();
                        break;
                    case 6:
                        ma.sortAssetByQuantity();
                        break;
                    case 7:
                        break;
                }
            } while (choice != 7);
        } else if (role == 2) {
            Menu menu = new Menu();
            menu.addNewOption("1. Search Asset By Name.");
            menu.addNewOption("2. Send Borrow Request");
            menu.addNewOption("3. Quit");
            
            ProgramForEmployee em = new ProgramForEmployee();
            em.readFile("D:\\DAO_MINH_TRI_LAB2\\asset.dat");

            int choice;
            do {
                System.out.println("");
                System.out.println("--------------Welcome to Asset Management Program---------------");
                menu.printMenu();
                System.out.println("----------------------------------------------------------------");
                choice = menu.getChoice();
                switch (choice) {

                    case 1:
                        em.searchByName();
                        break;
                    case 2:
                        em.sendRequest(l.employeeId);
                        break;                    
                    case 3:
                        break;
                }
            } while (choice != 3);
        } else {
            System.out.println("Login false!");
        }
    }

}
