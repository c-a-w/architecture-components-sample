package com.mainuser.budgetapp.util;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mainuser.budgetapp.MainActivity;
import com.mainuser.budgetapp.R;
import com.mainuser.budgetapp.detail.LineItemActivity;
import com.mainuser.budgetapp.list.ItemsListActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
        return true;
    }

    public void toAddLineItem(MenuItem menuItem) {
        Intent intent = new Intent(this, LineItemActivity.class);
        startActivity(intent);
    }

    public void toHome(MenuItem menuItem) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void toList(MenuItem menuItem) {
        Intent intent = new Intent(this, ItemsListActivity.class);
        startActivity(intent);
    }
}
