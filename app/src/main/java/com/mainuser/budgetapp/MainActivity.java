package com.mainuser.budgetapp;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mainuser.budgetapp.detail.LineItemViewModel;
import com.mainuser.budgetapp.util.BaseActivity;


public class MainActivity extends BaseActivity {

    private LineItemViewModel mLineItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
