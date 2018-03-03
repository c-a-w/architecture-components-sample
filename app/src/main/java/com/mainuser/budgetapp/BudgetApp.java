package com.mainuser.budgetapp;

import android.app.Application;

import com.mainuser.budgetapp.di.AppComponent;
import com.mainuser.budgetapp.di.BudgetAppModule;
import com.mainuser.budgetapp.di.DaggerAppComponent;


public class BudgetApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .budgetAppModule(new BudgetAppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
