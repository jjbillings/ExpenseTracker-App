package personal.jjbillings.expensetracker.MainActivity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import personal.jjbillings.expensetracker.Helpers.DBHelper;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by jbillz on 8/21/16.
 */
public class MainPresenterTest {

    private MainPresenter testPresenter;
    private DBHelper dbh;

    @Before
    public void setUp() throws Exception {
        dbh = mock(DBHelper.class);
        testPresenter = new MainPresenter(dbh);
        testPresenter.bindView(mock(MainView.class));
    }

    @After
    public void tearDown() throws Exception {
        testPresenter.unbindView();
    }

    @Test
    public void checkIfViewIsBound() {
        Assert.assertTrue(testPresenter.isViewAttached());
    }

}