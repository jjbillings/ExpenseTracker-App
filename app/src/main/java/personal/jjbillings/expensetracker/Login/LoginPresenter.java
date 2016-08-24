package personal.jjbillings.expensetracker.Login;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import personal.jjbillings.expensetracker.Helpers.DBHelper;
import personal.jjbillings.expensetracker.User;

/**
 * Created by jbillz on 8/17/16.
 */
public class LoginPresenter {

    private static final int MAX_LOGIN_ATTEMPT = 3;
    private int loginAttempt;
    private LoginView loginView;
    private List<String> usernames, passwords;
    private DBHelper mDBHelper;


    public LoginPresenter(LoginView loginView, DBHelper dbHelper) {
        this.loginView = loginView;
        this.mDBHelper = dbHelper;

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

    public boolean isUsernamePasswordEmpty(String username, String password) {
        if(username == null || password == null || username.equals("") || password.equals("")) {
            return false;
        }
        return true;
    }

    public void doLogin(String username, String password) {
        if(isLoginAttemptExceeded()) {
            loginView.showErrorMessageForMaxLoginAttempts();
            return;
        }

        if(!isUsernamePasswordEmpty(username,password)) {
            loginView.showErrorMessageForEmptyUserNamePassword();
            return;
        }

        //TODO: Check DB instead of arraylists.
        if(usernames.contains(username) && passwords.contains(password)) {
            loginView.login();
            return;
        }

        incrementLoginAttempt();
        loginView.showErrorMessageForUserNamePassword();
    }

    public void doRegisterUser(String username, String password) {
        if(doesUserExist(username)) {
            loginView.showErrorMessageIfUsernameTaken();
            return;
        }

        User newUser = new User(0,username,password);
        mDBHelper.addUser(newUser);
        loginView.showConfirmationForRegistration();
    }

    private boolean doesUserExist(String username) {

        if(mDBHelper.getUsersCount() < 1) {
            return false;
        }

        User containedUser = mDBHelper.getUser(username);

        if(containedUser == null) {
            return false;
        }

        return true;
    }
}
