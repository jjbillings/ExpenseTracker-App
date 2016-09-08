package personal.jjbillings.expensetracker.Models;

/**
 * Created by jbillz on 9/8/16.
 */
public class ExpenseCategory {

    private int id;
    private String name, description;

    private ExpenseCategory(Builder b) {
        this.id = b.id;
        this.name = b.name;
        this.description = b.description;
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

    public static class Builder {
        private final int id;
        private final String name;

        private String description;

        public Builder(int id, String name){
            this.id = id;
            this.name = name;
        }

        public Builder description(String desc) {
            this.description = desc;
            return this;
        }

        public ExpenseCategory build() {
            return new ExpenseCategory(this);
        }
    }
}
