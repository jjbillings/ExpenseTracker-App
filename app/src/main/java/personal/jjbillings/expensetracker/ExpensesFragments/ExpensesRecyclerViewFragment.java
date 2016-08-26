package personal.jjbillings.expensetracker.ExpensesFragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import personal.jjbillings.expensetracker.R;

/**
 * Created by jbillz on 8/25/16.
 */
public class ExpensesRecyclerViewFragment extends Fragment {

    private View view;
    @BindView(R.id.fragmentRecyclerView) RecyclerView recyclerView;

    public static ExpensesRecyclerViewFragment newInstance() {
        ExpensesRecyclerViewFragment frag = new ExpensesRecyclerViewFragment();
        frag.setRetainInstance(true); //TODO: Figure out what this does... if it's needed.
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_recyclerview,container,false);

            ButterKnife.bind(this,view);

        }

        return view;
    }

    //TODO:Add adapter...
}
