package personal.jjbillings.expensetracker.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jbillz on 9/2/16.
 */
public class Report {

    private int id;
    private String name, description;
    private Date createDate, startDate, endDate;
    private double totalSpent, averageSpent;
    private List<Expense> expenses;

    private Report(Builder b) {
        this.id = b.id;
        this.name = b.name;
        this.description = b.description;
        this.createDate = b.createDate;
        this.startDate = b.startDate;
        this.endDate = b.endDate;
        this.totalSpent = b.totalSpent;
        this.averageSpent = b.averageSpent;
        this.expenses = b.expenses;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getTotalSpent() {
        return totalSpent;
    }

    public double getAverageSpent() {
        return averageSpent;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    private static class Builder {
        private final int id;
        private final String name;
        private final Date createDate;
        private final double totalSpent, averageSpent;

        private String description;
        private Date startDate, endDate;
        private List<Expense> expenses;

        public Builder(int id, String name, Date cDate, double total, double average) {
            this.id = id;
            this.name = name;
            this.createDate = cDate;
            this.totalSpent = total;
            this.averageSpent = average;
        }

        public Builder expenses(List<Expense> e) {
            this.expenses = new ArrayList<>();
            this.expenses.addAll(e);
            return this;
        }

        public Builder description(String desc) {
            this.description = desc;
            return this;
        }

        public Builder startDate(Date startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(Date endDate) {
            this.endDate = endDate;
            return this;
        }

        public Report build() {
            return new Report(this);
        }
    }
}
