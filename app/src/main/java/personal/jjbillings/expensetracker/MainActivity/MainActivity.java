package personal.jjbillings.expensetracker.MainActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import personal.jjbillings.expensetracker.Dagger2.ExpenseApplication;
import personal.jjbillings.expensetracker.Helpers.DBHelper;
import personal.jjbillings.expensetracker.R;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter presenter;

    @Inject DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpenseApplication.getDBComponent().inject(this);
        initPresenter();
    }

    public void initPresenter() {
        presenter = new MainPresenter(this,mDBHelper);
    }

    public void initComponents() {

    }
}

