package personal.jjbillings.expensetracker.Login;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import personal.jjbillings.expensetracker.MainActivity.MainActivity;
import personal.jjbillings.expensetracker.R;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private EditText usernameEditText,passwordEditText;
    private Button btnLogin;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initPresenter();
        initComponents();
    }

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
    public void login() {
        Intent i = new Intent(this, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        finish();
    }

    private void initPresenter() {
        presenter = new LoginPresenter(this);
    }

    private void initComponents() {
        usernameEditText = (EditText)findViewById(R.id.usernameEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener((View view) -> {
            presenter.doLogin(usernameEditText.getText().toString().trim(),
                    passwordEditText.getText().toString().trim());

        });
    }
}
