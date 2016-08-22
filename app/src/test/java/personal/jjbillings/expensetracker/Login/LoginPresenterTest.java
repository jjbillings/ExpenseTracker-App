package personal.jjbillings.expensetracker.Login;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by jbillz on 8/17/16.
 */
public class LoginPresenterTest {

    private LoginView loginView;
    private LoginPresenter testPresenter;
    @Before
    public void setUp() throws Exception {
        loginView = mock(LoginView.class);
        testPresenter = new LoginPresenter(loginView);
    }

    @After
    public void tearDown() throws Exception {

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

    @Test
    public void checkIfUsernameAndPasswordIsCorrect() {
        testPresenter.doLogin("jbillz","password");
        verify(loginView).login();
    }

    @Test
    public void checkIfUsernameAndPasswordIsIncorrect() {
        testPresenter.doLogin("j$","password");
        verify(loginView).showErrorMessageForUserNamePassword();
    }

    @Test
    public void checkIfLoginAttemptsIsExceededAndViewMethodCalled() {
        //LoginView loginView = mock(LoginView.class);
        //LoginPresenter testPresenter = new LoginPresenter(loginView);
        testPresenter.doLogin("j$","password");
        testPresenter.doLogin("j$","password");
        testPresenter.doLogin("j$","password");
        testPresenter.doLogin("j$","password");
        verify(loginView).showErrorMessageForMaxLoginAttempts();
    }

    @Test
    public void checkIfUsernameFieldIsEmpty() {
        testPresenter.doLogin("","password");
        verify(loginView).showErrorMessageForEmptyUserNamePassword();
    }

    @Test
    public void checkIfPasswordFieldIsEmpty() {
        testPresenter.doLogin("jbillz","");
        verify(loginView).showErrorMessageForEmptyUserNamePassword();
    }

    @Test
    public void checkIfUsernameFieldIsNull() {
        testPresenter.doLogin(null,"password");
        verify(loginView).showErrorMessageForEmptyUserNamePassword();
    }

    @Test
    public void checkIfPasswordFieldIsNull() {
        testPresenter.doLogin("jbillz",null);
        verify(loginView).showErrorMessageForEmptyUserNamePassword();
    }
}