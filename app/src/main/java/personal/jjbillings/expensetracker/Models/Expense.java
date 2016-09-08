package personal.jjbillings.expensetracker.Models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jbillz on 8/25/16.
 */
public class Expense {

    private String name;
    private String description;
    private double amount;
    private Date date;
    private int id;
    private int userId;
    private List<ExpenseCategory> expenseCategories;
    private List<PaymentMethod> paymentMethods;
    private List<ReceiptImage> receipts;

    //privatize c-tor
    private Expense(Expense.Builder b) {
        this.name = b.name;
        this.description = b.description;
        this.amount = b.amount;
        this.date = b.date;
        this.id = b.id;
        this.userId = b.userId;
        this.expenseCategories = b.expenseCategories;
        this.paymentMethods = b.paymentMethods;
        this.receipts = b.receipts;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public List<ExpenseCategory> getExpenseCategories() {
        return expenseCategories;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public List<ReceiptImage> getReceipts() {
        return receipts;
    }

    public static class Builder {

        //Required
        private final String name;
        private final double amount;
        private final Date date;
        private final int id, userId;

        //Optional
        private String description;
        private List<ExpenseCategory> expenseCategories;
        private List<PaymentMethod> paymentMethods;
        private List<ReceiptImage> receipts;

        public Builder(String name, double amt, Date date, int id, int userid) {
            this.name = name;
            this.amount = amt;
            this.date = date;
            this.id = id;
            this.userId = userid;
        }

        public Builder description(String desc) {
            this.description = desc;
            return this;
        }

        public Builder expenseCategories(List<ExpenseCategory> expenseCategories) {
            this.expenseCategories = new ArrayList<>();
            this.expenseCategories.addAll(expenseCategories);
            return this;
        }

        public Builder paymentMethods(List<PaymentMethod> paymentMethods) {
            this.paymentMethods = new ArrayList<>();
            this.paymentMethods.addAll(paymentMethods);
            return this;
        }

        public Builder receipts(List<ReceiptImage> receipts) {
            this.receipts = new ArrayList<>();
            this.receipts.addAll(receipts);
            return this;
        }

        public Expense build() {
            return new Expense(this);
        }
    }
    /* save for default db creation
    public enum ExpenseCategory {
        GROCERIES,
        RESTAURANT,
        PET_FOOD,

        RENT,
        PROPERTY_TAX,
        HOUSEHOLD_REPAIRS,
        MORTGAGE, //Not super applicable for me...

        TELEPHONE,
        CABLE,
        INTERNET,
        ELECTRICITY,
        HEATING,
        WATER,

        CLOTHING,

        FUEL,
        TRAVEL,
        VEHICLE_MAINTENANCE_MAJOR,
        VEHICLE_MAINTENANCE_MINOR,
        VEHICLE_MISC,

        INSURANCE_HEALTH,
        INSURANCE_AUTO,
        INSURANCE_HOMEOWNER,
        INSURANCE_LIFE,

        SCHOOL,
        FRIVOLOUS,
        WAGES,
        DONATION;

    }
    */
}
