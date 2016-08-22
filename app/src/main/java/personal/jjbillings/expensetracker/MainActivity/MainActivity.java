package personal.jjbillings.expensetracker.MainActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import personal.jjbillings.expensetracker.R;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPresenter();
    }

    public void initPresenter() {
        presenter = new MainPresenter(this);
    }

    public void initComponents() {

    }
}

