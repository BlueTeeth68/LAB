package userdata;

/**
 *
 * @author tri
 */
public abstract class User implements Comparable<User>{
    protected String id, name, birthday, role, sex, password;

    public User(String id, String name, String birthday, String role, String sex, String password) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.role = role;
        this.sex = sex;
        this.password = password;
    } 

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public abstract void showInformation ();
    
    public int compareTo(User that) {
        return this.id.compareToIgnoreCase(that.id);
    }
    
}
