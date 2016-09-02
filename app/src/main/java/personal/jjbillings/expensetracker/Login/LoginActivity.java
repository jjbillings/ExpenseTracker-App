package personal.jjbillings.expensetracker.Login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import personal.jjbillings.expensetracker.Dagger2.ExpenseApplication;
import personal.jjbillings.expensetracker.Helpers.DBHelper;
import personal.jjbillings.expensetracker.MainActivity.MainActivity;
import personal.jjbillings.expensetracker.R;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.usernameEditText) EditText usernameEditText;
    @BindView(R.id.passwordEditText) EditText passwordEditText;
    @BindView(R.id.btnLogin) Button btnLogin;
    @BindView(R.id.btnRegister) Button btnRegister;

    @Inject DBHelper mDBHelper;

    private LoginPresenter presenter;

    //TODO: Add Lifecycle management for handling the viewbinding for the presenter.
    //Setup.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        //inject the database.
        ExpenseApplication.getDBComponent().inject(this);
        initPresenter();

        usernameEditText.setText("jbillz");
        passwordEditText.setText("password");
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
    }

    private void initPresenter() {
        presenter = new LoginPresenter(mDBHelper);
        presenter.bindView(this);
    }

    //Methods called by Presenter
    @Override
    public void showErrorMessageForUserNamePassword() {
        Snackbar.make(passwordEditText,"Invalid username/password", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessageForMaxLoginAttempts() {
        Snackbar.make(btnLogin,"Too many invalid logins... try again later", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessageForEmptyUserNamePassword() {
        Snackbar.make(btnLogin,"Please enter a username/password", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessageIfUsernameTaken() {
        Snackbar.make(btnRegister,"Apologies, that username has already been taken", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showConfirmationForRegistration() {
        Snackbar.make(btnRegister,"Congratulations, you have been registered!", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void login() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    //Button Listeners
    @OnClick(R.id.btnLogin)
    protected void btnLoginOnClick() {
        presenter.doLogin(usernameEditText.getText().toString().trim(),
                passwordEditText.getText().toString().trim());
    }

    @OnClick(R.id.btnRegister)
    protected void btnRegisterOnClick() {
        presenter.doRegisterUser(usernameEditText.getText().toString().trim(),
                passwordEditText.getText().toString().trim());
    }
}
