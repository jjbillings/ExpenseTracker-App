package personal.jjbillings.expensetracker.Login;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import personal.jjbillings.expensetracker.Helpers.DBHelper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by jbillz on 8/17/16.
 */
public class LoginPresenterTest {

    //private LoginView loginView;
    private DBHelper dbh;
    private LoginPresenter testPresenter;

    @Before
    public void setUp() throws Exception {
        //TODO: Instead of mocking, actually instantiate a db?
        dbh = mock(DBHelper.class);
        testPresenter = new LoginPresenter(dbh);
        testPresenter.bindView(mock(LoginView.class));
    }

    @After
    public void tearDown() throws Exception {
        testPresenter.unbindView();
    }

    @Test
    public void checkIfViewIsBound() {
        Assert.assertTrue(testPresenter.isViewAttached());
    }

    @Test
    public void checkIfLoginAttemtIsExceeded() {
        Assert.assertEquals(1,testPresenter.incrementLoginAttempt());
        Assert.assertEquals(2,testPresenter.incrementLoginAttempt());
        Assert.assertEquals(3,testPresenter.incrementLoginAttempt());
        Assert.assertTrue(testPresenter.isLoginAttemptExceeded());
    }

    @Test
    public void checkIfLoginAttemptIsNotExceeded() {
        Assert.assertFalse(testPresenter.isLoginAttemptExceeded());
    }

    //TODO: This test always fails because we're not working with an actual DB, just a mock...
    @Test
    public void checkIfUsernameAndPasswordIsCorrect() {
        String un = "jbillz";
        String pw = "password";
        testPresenter.doRegisterUser(un,pw);
        testPresenter.doLogin(un,pw);
        verify(testPresenter.getView()).login();
    }

    @Test
    public void checkIfUsernameAndPasswordIsIncorrect() {
        testPresenter.doLogin("j$","password");
        verify(testPresenter.getView()).showErrorMessageForUserNamePassword();
    }

    @Test
    public void checkIfLoginAttemptsIsExceededAndViewMethodCalled() {
        //LoginView loginView = mock(LoginView.class);
        //LoginPresenter testPresenter = new LoginPresenter(loginView);

        testPresenter.doLogin("j$","password");
        testPresenter.doLogin("j$","password");
        testPresenter.doLogin("j$","password");
        testPresenter.doLogin("j$","password");
        verify(testPresenter.getView()).showErrorMessageForMaxLoginAttempts();
    }

    @Test
    public void checkIfUsernameFieldIsEmpty() {
        testPresenter.doLogin("","password");
        Assert.assertFalse(testPresenter.isUsernamePasswordEmpty("","password"));
        verify(testPresenter.getView()).showErrorMessageForEmptyUserNamePassword();
    }

    @Test
    public void checkIfPasswordFieldIsEmpty() {
        testPresenter.doLogin("jbillz","");
        Assert.assertFalse(testPresenter.isUsernamePasswordEmpty("jbillz",""));
        verify(testPresenter.getView()).showErrorMessageForEmptyUserNamePassword();
    }

    @Test
    public void checkIfUsernameFieldIsNull() {
        testPresenter.doLogin(null,"password");
        Assert.assertFalse(testPresenter.isUsernamePasswordEmpty(null,"password"));
        verify(testPresenter.getView()).showErrorMessageForEmptyUserNamePassword();
    }

    @Test
    public void checkIfPasswordFieldIsNull() {
        testPresenter.doLogin("jbillz",null);
        Assert.assertFalse(testPresenter.isUsernamePasswordEmpty("jbillz",null));
        verify(testPresenter.getView()).showErrorMessageForEmptyUserNamePassword();
    }

    @Test
    public void checkIfRegistrationHappens() {
        testPresenter.doRegisterUser("newuser","newpassword");
        verify(testPresenter.getView()).showConfirmationForRegistration();
    }
}
