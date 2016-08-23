package personal.jjbillings.expensetracker;

import android.app.Application;


import personal.jjbillings.expensetracker.Dagger2.DaggerDatabaseComponent;
import personal.jjbillings.expensetracker.Dagger2.DatabaseComponent;
import personal.jjbillings.expensetracker.Dagger2.DatabaseModule;

/**
 * Created by jbillz on 8/22/16.
 */
public class ExpenseApplication extends Application {


    private static DatabaseComponent dbComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        dbComponent = DaggerDatabaseComponent.builder().databaseModule(new DatabaseModule(this)).build();
    }

    public static DatabaseComponent getDBComponent() {
        return dbComponent;
    }

}
