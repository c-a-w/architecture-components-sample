package com.mainuser.budgetapp.list;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.media.browse.MediaBrowser;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.mainuser.budgetapp.BudgetApp;
import com.mainuser.budgetapp.R;
import com.mainuser.budgetapp.ViewModelFactory;
import com.mainuser.budgetapp.database.LineItem;
import com.mainuser.budgetapp.util.BaseActivity;

import java.util.List;

import javax.inject.Inject;

public class ItemsListActivity extends BaseActivity {
    private static final String TAG = ItemsListActivity.class.getSimpleName();

    private ItemsListViewModel viewModel;
    private RecyclerView recyclerView;
    private LineItemListAdapter adapter;
    private List<LineItem> itemsList;

    @Inject ViewModelFactory factory;
    RecyclerView.LayoutManager layoutManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((BudgetApp) getApplication()).getAppComponent()
                .inject(this);

        setContentView(R.layout.activity_items_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewModel = ViewModelProviders.of(this, factory).get(ItemsListViewModel.class);

        recyclerView = findViewById(R.id.items_list_recycler);
        recyclerView.setHasFixedSize(true);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDelete);
        itemTouchHelper.attachToRecyclerView(recyclerView);


        viewModel.getList().observe(this, new Observer<List<LineItem>>() {
            @Override
            public void onChanged(@Nullable List<LineItem> lineItems) {
                if (lineItems == null) {
                    Log.d(TAG, "lineItems is null");
                } else {
                    updateItemsList(lineItems);
                }
                for (LineItem li : itemsList) {
                    Log.d(TAG, li.toString());
                }
            }
        });
    }

    private void updateItemsList(List<LineItem> newList) {
        itemsList = newList;
        adapter = new LineItemListAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setItemsList(newList);
    }

    private ItemTouchHelper.Callback swipeToDelete =
            new ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.ACTION_STATE_IDLE,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            int id = viewHolder.getAdapterPosition();
            viewModel.deleteLineItem(adapter.get(id));
            adapter.notifyItemRemoved(id);
        }
    };
}
