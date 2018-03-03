package com.mainuser.budgetapp.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.mainuser.budgetapp.BudgetApp;
import com.mainuser.budgetapp.ViewModelFactory;
import com.mainuser.budgetapp.database.LineItem;
import com.mainuser.budgetapp.util.BaseActivity;
import com.mainuser.budgetapp.R;

import java.util.List;

import javax.inject.Inject;

public class ItemsListActivity extends BaseActivity {
    private static final String TAG = ItemsListActivity.class.getSimpleName();

    private ItemsListViewModel viewModel;
    private RecyclerView recyclerView;
    private CustomListAdapter adapter;

    @Inject ViewModelFactory factory;
    @Inject RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BudgetApp) getApplication()).getAppComponent()
                .inject(this);

        setContentView(R.layout.activity_items_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new CustomListAdapter(this);
        viewModel = ViewModelProviders.of(this, factory).get(ItemsListViewModel.class);
        viewModel.getList().observe(this, new Observer<List<LineItem>>() {
            @Override
            public void onChanged(@Nullable List<LineItem> lineItems) {
                Log.d(TAG, "calling observe onChanged");
                if (lineItems == null) {
                    Log.d(TAG, "lineItems null in observe()");
                    return;
                }
                adapter.setItemsList(lineItems);
            }
        });

        recyclerView = findViewById(R.id.items_list_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
     }
}
