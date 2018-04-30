package com.mainuser.budgetapp.detail;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

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
        return repository.getLineItem(id);
    }

    void updateLineItem(LineItem lineItem) {
        repository.updateLineItem(lineItem);
    }

}
