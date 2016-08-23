package personal.jjbillings.expensetracker.MainActivity;

import personal.jjbillings.expensetracker.Helpers.DBHelper;

/**
 * Created by jbillz on 8/17/16.
 */
public class MainPresenter {

    private MainView mainView;
    private DBHelper mDBHelper;

    public  MainPresenter(MainView mv, DBHelper dbh) {
        this.mainView = mv;
        this.mDBHelper = dbh;
    }
}
