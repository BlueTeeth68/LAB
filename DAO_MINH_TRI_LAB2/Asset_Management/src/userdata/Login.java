package userdata;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import tool.Tool;

/**
 *
 * @author tri
 */
public class Login {
    public String employeeId;

    public Login() {
    }   
    
    private ArrayList<User> u = new ArrayList<User>();

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
                    String id, name, birthday, role, sex, password;
                    id = data[0].toString();
                    name = data[1].toString();
                    birthday = data[2].toString();
                    role = data[3].toString();
                    sex = data[4].toString();
                    password = data[5].toString();
                    if (role.equalsIgnoreCase("MA")) {
                        u.add(new Manager(id, name, birthday, role, sex, password));
                    } else if (role.equalsIgnoreCase("EM")) {
                        u.add(new Employee(id, name, birthday, role, sex, password));
                    }
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
            for (User x : u) {
                String data = x.getId() + ";" + x.getName() + ";" + x.getBirthday() + ";"
                        + x.getRole() + ";" + x.getSex() + ";" + x.getPassword();
                myWriter.write(data + "\n");
            }
            myWriter.close();
            System.out.println("Store data successfully.");
        } catch (Exception e) {
            System.out.println("An error occured when writing file!");
            e.printStackTrace();
        }
    }

    public boolean isEmpty() {
        return u.isEmpty();
    }

    public int checkId(String id) {
        if (isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < u.size(); i++) {
                if (u.get(i).getId().equalsIgnoreCase(id)) {
                    return i;
                }
            }
            return -1;
        }
    }

    public int logIn() {
        String id = null, password;
        if (isEmpty()) {
            System.out.println("Cann't log in! Data are empty!");
            return -1;
        } else {
            int pos = -1;
            while (pos == -1) {
                id = Tool.getID("Input ID: ", "ID must be in form of"
                        + " Exxxxxx!", "^[e|E]\\d{6}$").toUpperCase();
                pos = checkId(id);                 
                if (pos == -1) {                   
                    System.out.println("Id doesn't exist!");
                }
            }
            password = Tool.getString("Input password: ", "Password cann't be empty!");
            if (u.get(pos).getPassword().equalsIgnoreCase(password)) {
                if (u.get(pos).getRole().equalsIgnoreCase("MA")) {
                    System.out.println("You're logged in as manager role.");
                    this.employeeId = id;
                    return 1;
                }
                if (u.get(pos).getRole().equalsIgnoreCase("EM")) {
                    System.out.println("You're logged in as employee role.");
                    this.employeeId = id;
                    return 2;
                }
            } else {
                System.out.println("Incorrect ID or password!");
                return -1;
            }
        }
        return -1;
    }
}
