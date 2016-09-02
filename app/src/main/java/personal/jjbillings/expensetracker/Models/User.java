package personal.jjbillings.expensetracker.Models;

import java.util.List;

/**
 * Created by jbillz on 8/22/16.
 */
public class User {

    private String username;
    private String password;
    private List<Expense> expenses;

    public User() {

    }

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(String username, String password, List<Expense> expenses) {
        this.setUsername(username);
        this.setPassword(password);
        this.setExpenses(expenses);
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

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }
}
