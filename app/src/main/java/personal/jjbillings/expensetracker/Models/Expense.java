package personal.jjbillings.expensetracker.Models;

import java.util.Date;
import java.util.List;

/**
 * Created by jbillz on 8/25/16.
 */
public class Expense {

    private String description;
    private double amount;
    private Date date;
    private List<ExpenseCategory> expenseCategories;


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
}
