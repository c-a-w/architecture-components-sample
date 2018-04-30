package com.mainuser.budgetapp.list;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mainuser.budgetapp.database.LineItem;
import com.mainuser.budgetapp.database.LineItemRepository;

import java.util.List;


public class ItemsListViewModel extends ViewModel {
    private static final String TAG = ItemsListViewModel.class.getSimpleName();

    private LineItemRepository repository;
    @SuppressLint("StaticFieldLeak")
    private Application application;

    public ItemsListViewModel(Application application, LineItemRepository repository) {
        this.application = application;
        this.repository = repository;
    }

    public LiveData<List<LineItem>> getList() {
        Log.d(TAG, "calling vm getList");
        return repository.getLineItems();
    }

    public LiveData<LineItem> getLineItem(int id) {
        Log.d(TAG, "getting line item with id: " + id);
        return repository.getLineItem(id);
    }


}
