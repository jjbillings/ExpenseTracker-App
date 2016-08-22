package personal.jjbillings.expensetracker.Login;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jbillz on 8/17/16.
 */
public class LoginPresenter {

    private static final int MAX_LOGIN_ATTEMPT = 3;
    private int loginAttempt;
    private LoginView loginView;
    private List<String> usernames, passwords;


    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;

        usernames = new ArrayList<String>();
        passwords = new ArrayList<String>();

        usernames.add("jbillz");
        passwords.add("password");

        loginAttempt = 0;
    }

    public int incrementLoginAttempt() {
        loginAttempt += 1;
        return loginAttempt;
    }

    public boolean isLoginAttemptExceeded() {
        return loginAttempt >= MAX_LOGIN_ATTEMPT;
    }


    public void doLogin(String username, String password) {
        if(isLoginAttemptExceeded()) {
            loginView.showErrorMessageForMaxLoginAttempts();
            return;
        }

        if(username == null || password == null || username.equals("") || password.equals("")) {
            loginView.showErrorMessageForEmptyUserNamePassword();
            return;
        }

        if(usernames.contains(username) && passwords.contains(password)) {
            loginView.login();
            return;
        }

        incrementLoginAttempt();
        loginView.showErrorMessageForUserNamePassword();
    }
}
