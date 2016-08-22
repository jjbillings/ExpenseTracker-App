package personal.jjbillings.expensetracker.MainActivity;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * Created by jbillz on 8/21/16.
 */
public class MainPresenterTest {

    private MainPresenter testPresenter;
    private MainView mainView;

    @Before
    public void setUp() throws Exception {
        mainView = mock(MainView.class);
        testPresenter = new MainPresenter(mainView);
    }

    @After
    public void tearDown() throws Exception {

    }


}