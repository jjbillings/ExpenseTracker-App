package personal.jjbillings.expensetracker.Models;

import java.util.List;

/**
 * TODO: Refactor constructor/possibly into builder.
 * Created by jbillz on 8/22/16.
 */
public class User {

    private String username, password;
    private int id;
    private List<Expense> expenses;
    private List<PaymentMethod> paymentMethods;
    private List<Report> reports;

    public User() {

    }

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public User(int id, String username, String password) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    public User(String username, String password, List<Expense> expenses, List<PaymentMethod> paymentMethods) {
        this.username = username;
        this.password = password;
        this.expenses = expenses;
        this.paymentMethods = paymentMethods;
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

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }
}
