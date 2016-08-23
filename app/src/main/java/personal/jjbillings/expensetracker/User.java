package personal.jjbillings.expensetracker;

/**
 * Created by jbillz on 8/22/16.
 */
public class User {

    private String username;
    private String password;
    private int id;

    public User() {

    }

    public User(int id, String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
        this.setId(id);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
