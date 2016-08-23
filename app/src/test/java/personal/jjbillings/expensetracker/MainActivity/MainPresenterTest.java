package personal.jjbillings.expensetracker.MainActivity;

import org.junit.After;
import org.junit.Before;

import personal.jjbillings.expensetracker.Helpers.DBHelper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by jbillz on 8/21/16.
 */
public class MainPresenterTest {

    private MainPresenter testPresenter;
    private DBHelper dbh;
    private MainView mainView;

    @Before
    public void setUp() throws Exception {
        mainView = mock(MainView.class);
        dbh = mock(DBHelper.class);
        testPresenter = new MainPresenter(mainView,dbh);
    }

    @After
    public void tearDown() throws Exception {

    }


}