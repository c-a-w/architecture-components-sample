package com.mainuser.budgetapp.di;

import com.mainuser.budgetapp.ViewModelFactory;
import com.mainuser.budgetapp.detail.LineItemActivity;
import com.mainuser.budgetapp.list.ItemsListActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = BudgetAppModule.class)
public interface AppComponent {
    void inject(ViewModelFactory viewModelFactory);
    void inject(ItemsListActivity itemsListActivity);
    void inject(LineItemActivity lineItemActivity);
}
