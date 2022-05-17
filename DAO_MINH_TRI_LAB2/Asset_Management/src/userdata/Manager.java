package userdata;

/**
 *
 * @author tri
 */
public class Manager extends User{

    public Manager(String id, String name, String birthday, String role, String sex, String password) {
        super(id, name, birthday, role, sex, password);
    }
    
    public void showInformation () {
        String msg;
        msg = String.format("|%-10s|%-20s|%-12s|%-6s|%-4s|",id, name, birthday, role, sex);
        System.out.println(msg);
    }
}
