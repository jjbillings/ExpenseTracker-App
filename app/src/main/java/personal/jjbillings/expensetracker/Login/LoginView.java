package personal.jjbillings.expensetracker.Login;

/**
 * Created by jbillz on 8/17/16.
 */
public interface LoginView {

    void showErrorMessageForUserNamePassword();

    void showErrorMessageForMaxLoginAttempts();

    void showErrorMessageForEmptyUserNamePassword();

    void showErrorIfUsernameTaken();

    void login();
}
