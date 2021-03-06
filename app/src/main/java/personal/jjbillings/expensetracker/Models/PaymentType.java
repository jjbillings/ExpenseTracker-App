package personal.jjbillings.expensetracker.Models;

/**
 * Created by jbillz on 8/25/16.
 */
public class PaymentType {

    private int id;
    private String name, description;

    private PaymentType(Builder b) {
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

    private static class Builder {
        private final int id;
        private final String name;
        private String description;

        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder description(String desc) {
            this.description = desc;
            return this;
        }

        public PaymentType build() {
            return new PaymentType(this);
        }
    }
}
/* keep for default table creation...
public enum PaymentType {
     CREDIT_CARD,
     DEBIT_CARD,
     ACCOUNT_BALANCE,
     PAYPAL,
     VENMO,
     CASH,
     GIFT_CARD;
};
*/
