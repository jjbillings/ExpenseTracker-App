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

    @Override
    public boolean equals(Object o) {

        if(o == this) {
            return true;
        }

        if(!(o instanceof User)) {
            return false;
        }

        //TODO: Check more conditions?
        if(((User)o).getUsername().equals(this.getUsername())) {
            return true;
        }

        return false;
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
