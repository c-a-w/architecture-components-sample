package com.mainuser.budgetapp.detail;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.mainuser.budgetapp.database.LineItem;
import com.mainuser.budgetapp.database.LineItemRepository;
import com.mainuser.budgetapp.list.ItemsListViewModel;

public class LineItemViewModel extends ViewModel {
    private static final String TAG = ItemsListViewModel.class.getSimpleName();

    private LineItemRepository repository;
    private LiveData<LineItem> lineItem;
    private Application application;

    public LineItemViewModel(Application application, LineItemRepository repository) {
        this.application = application;
        this.repository = repository;
    }

    LiveData<LineItem> getLineItemAt(int id) {
        LiveData<LineItem> currentLineItem = repository.getLineItem(id);
        lineItem = currentLineItem;
        return currentLineItem;
    }

    void updateLineItem(long newDate, String newDescription, double newAmount, String newCategory) {
        if (null == lineItem) {
            Log.d(TAG, "lineItem not set");
            return;
        }
        LineItem newItem = lineItem.getValue();
        if (null == newItem) {
            Log.d(TAG, "No value for " + lineItem);
            return;
        }
        newItem.setDate(newDate);
        newItem.setDescription(newDescription);
        newItem.setAmount(newAmount);
        newItem.setCategory(newCategory);
        repository.updateLineItem(newItem);
    }

}
