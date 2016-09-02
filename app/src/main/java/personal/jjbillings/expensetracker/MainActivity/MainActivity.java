package personal.jjbillings.expensetracker.MainActivity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.jjbillings.expensetracker.Dagger2.ExpenseApplication;
import personal.jjbillings.expensetracker.ExpensesFragments.ExpensesRecyclerViewFragment;
import personal.jjbillings.expensetracker.Helpers.DBHelper;
import personal.jjbillings.expensetracker.R;

public class MainActivity extends AppCompatActivity implements MainView{

    private MainPresenter presenter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fragment_container) FrameLayout fragContainer;

    @Inject DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        ExpenseApplication.getDBComponent().inject(this);
        initPresenter();
        initComponents();
        initToolbar();
    }

    @Override
    protected void onDestroy() {
        presenter.unbindView();
        super.onDestroy();
    }

    public void initPresenter() {
        presenter = new MainPresenter(mDBHelper);
        presenter.bindView(this);
    }

    public void initComponents() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, ExpensesRecyclerViewFragment.newInstance()).commit();
    }

    public void initToolbar() {
        setSupportActionBar(toolbar);
    }
}

