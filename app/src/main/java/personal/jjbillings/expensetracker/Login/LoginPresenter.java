package personal.jjbillings.expensetracker.Login;

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
    private DBHelper dbh;


    public LoginPresenter(DBHelper dbHelper) {
        this.dbh = dbHelper;
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

        if(!doesUserExist(username)) {
            getView().showErrorMessageForUserNamePassword();
            incrementLoginAttempt();
            return;
        }

        if(usernamePasswordMatch(username, password)) {
            getView().login();
            return;
        }

        incrementLoginAttempt();
        getView().showErrorMessageForUserNamePassword();
    }

    public void doRegisterUser(String username, String password) {

        if(doesUserExist(username)) {
            getView().showErrorMessageIfUsernameTaken();
            return;
        }

        User newUser = dbh.addUser(username,password);
        getView().showConfirmationForRegistration();
    }

    private boolean doesUserExist(String username) {

        if(dbh.getEntryCount(dbh.TABLE_USERS) < 1) {
            return false;
        }

        if(dbh.getUser(username) == null) {
            return false;
        }

        return true;
    }

    private boolean usernamePasswordMatch(String username, String incomingPassword) {
        User storedUser = dbh.getUser(username);

        if(storedUser.getPassword().equals(incomingPassword)) {
            return true;
        }

        return false;
    }
}
