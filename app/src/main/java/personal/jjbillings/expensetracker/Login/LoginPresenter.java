package personal.jjbillings.expensetracker.Login;

import android.util.Log;

import personal.jjbillings.expensetracker.BasePresenter;
import personal.jjbillings.expensetracker.Helpers.DBHelper;
import personal.jjbillings.expensetracker.Models.User;

/**
 * TODO: Add checks to ensure view is still bound before calling "getView()"
 * Created by jbillz on 8/17/16.
 */
public class LoginPresenter extends BasePresenter<LoginView>{

    private static final int MAX_LOGIN_ATTEMPT = 3;
    private int loginAttempt;

    //private LoginView loginView;
    private DBHelper mDBHelper;


    public LoginPresenter(DBHelper dbHelper) {
        this.mDBHelper = dbHelper;
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
            getView().showErrorMessageForMaxLoginAttempts();
            return;
        }

        if(!isUsernamePasswordEmpty(username,password)) {
            getView().showErrorMessageForEmptyUserNamePassword();
            return;
        }

        User attemptingUser = new User(username,password);

        if(!doesUserExist(attemptingUser)) {
            getView().showErrorMessageForUserNamePassword();
            incrementLoginAttempt();
            return;
        }

        if(usernamePasswordMatch(attemptingUser)) {
            getView().login();
            return;
        }

        incrementLoginAttempt();
        getView().showErrorMessageForUserNamePassword();
    }

    public void doRegisterUser(String username, String password) {

        User newUser = new User(username,password);
        if(doesUserExist(newUser)) {
            getView().showErrorMessageIfUsernameTaken();
            return;
        }

        mDBHelper.addUser(newUser);
        getView().showConfirmationForRegistration();
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
