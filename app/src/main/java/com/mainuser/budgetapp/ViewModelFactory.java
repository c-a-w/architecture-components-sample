package com.mainuser.budgetapp;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.mainuser.budgetapp.database.LineItemRepository;
import com.mainuser.budgetapp.detail.LineItemActivity;
import com.mainuser.budgetapp.detail.LineItemViewModel;
import com.mainuser.budgetapp.list.ItemsListViewModel;

import javax.inject.Inject;


public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    public static final String TAG = ViewModelFactory.class.getSimpleName();
    private final Application application;
    private final LineItemRepository repository;

    @Inject
    public ViewModelFactory(Application application, LineItemRepository repository) {
        this.application = application;
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ItemsListViewModel.class)) {
            return (T) new ItemsListViewModel(application, repository);
        }
        if (modelClass.isAssignableFrom(LineItemViewModel.class)) {
            return (T) new LineItemViewModel(application, repository);
        }
        throw new IllegalArgumentException(
                "ViewModel not recognized: " + modelClass.getCanonicalName());
    }
}
