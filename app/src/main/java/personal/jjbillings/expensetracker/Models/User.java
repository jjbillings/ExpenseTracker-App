package personal.jjbillings.expensetracker.Models;

/**
 * Created by jbillz on 8/22/16.
 */
public class User {

    private String username;
    private String password;

    public User() {

    }

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
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

}
