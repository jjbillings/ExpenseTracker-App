package personal.jjbillings.expensetracker.Models;

/**
 * Created by jbillz on 9/2/16.
 */
public class PaymentMethod {

    private PaymentType paymentType;
    private int id;
    private String name;
    private String description;
    private double amountAvailable;

    private PaymentMethod(Builder builder) {
        this.paymentType = builder.paymentType;
        this.name = builder.name;
        this.description = builder.description;
        this.amountAvailable = builder.amountAvailable;
        this.id = builder.id;
    }


    /**
     * Builder class!
     */
    private static class Builder {
        //Required
        private final PaymentType paymentType;
        private final String name;
        private final int id;

        //Optional
        private String description;
        private double amountAvailable;

        public Builder(PaymentType pt, String nm, int id) {
            this.paymentType = pt;
            this.name = nm;
            this.id = id;
        }

        public Builder description(String desc) {
            this.description = desc;
            return this;
        }

        public Builder amountAvailable(double amt) {
            this.amountAvailable = amt;
            return this;
        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
        }
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmountAvailable() {
        return amountAvailable;
    }

    public void setAmountAvailable(double amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public int getId() {
        return id;
    }
}
