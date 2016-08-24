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

        User attemptingUser = new User(0,username,password);

        if(!doesUserExist(attemptingUser)) {
            loginView.showErrorMessageForUserNamePassword();
            incrementLoginAttempt();
            return;
        }

        if(usernamePasswordMatch(attemptingUser)) {
            loginView.login();
            return;
        }

        incrementLoginAttempt();
        loginView.showErrorMessageForUserNamePassword();
    }

    public void doRegisterUser(String username, String password) {

        User newUser = new User(0,username,password);
        if(doesUserExist(newUser)) {
            loginView.showErrorMessageIfUsernameTaken();
            return;
        }

        mDBHelper.addUser(newUser);
        loginView.showConfirmationForRegistration();
    }

    private boolean doesUserExist(User user) {

        if(mDBHelper.getUsersCount() < 1) {
            return false;
        }

        if(!mDBHelper.getAllUsers().contains(user)) {
            return false;
        }

        return true;
    }

    private boolean usernamePasswordMatch(User user) {
        String password = mDBHelper.getPassword(user);

        if(user.getPassword().equals(password)) {
            return true;
        }

        return false;
    }
}
