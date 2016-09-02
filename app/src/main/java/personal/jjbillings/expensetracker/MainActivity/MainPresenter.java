package personal.jjbillings.expensetracker.MainActivity;

import personal.jjbillings.expensetracker.BasePresenter;
import personal.jjbillings.expensetracker.Helpers.DBHelper;

/**
 * Created by jbillz on 8/17/16.
 */
public class MainPresenter extends BasePresenter<MainView>{

    private DBHelper mDBHelper;

    public MainPresenter(DBHelper dbh) {
        this.mDBHelper = dbh;
    }
}
