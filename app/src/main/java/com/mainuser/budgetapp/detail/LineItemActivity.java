package com.mainuser.budgetapp.detail;

import android.app.DialogFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.mainuser.budgetapp.BudgetApp;
import com.mainuser.budgetapp.R;
import com.mainuser.budgetapp.ViewModelFactory;
import com.mainuser.budgetapp.database.LineItem;
import com.mainuser.budgetapp.util.BaseActivity;
import com.mainuser.budgetapp.util.IntentStrings;
import com.mainuser.budgetapp.util.StringFormats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class LineItemActivity extends BaseActivity {
    private static final String TAG = LineItemActivity.class.getSimpleName();

    @Inject
    ViewModelFactory factory;
    private int lineItemId;
    private TextView dateView;
    private EditText descView;
    private EditText amountView;
    private EditText categoryView;
    private LineItemViewModel lineItemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_item);
        dateView = findViewById(R.id.input_date);
        descView = findViewById(R.id.input_desc);
        amountView = findViewById(R.id.input_amount);
        categoryView = findViewById(R.id.input_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((BudgetApp) getApplication()).getAppComponent().inject(this);

        lineItemViewModel = ViewModelProviders.of(this, factory).get(LineItemViewModel.class);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(IntentStrings.LINE_ITEM_ID)) {
            lineItemId = intent.getIntExtra(IntentStrings.LINE_ITEM_ID, -1);
        }
        lineItemViewModel.getLineItemAt(lineItemId).observe(this, new Observer<LineItem>() {
            @Override
            public void onChanged(@Nullable LineItem lineItem) {
                if (null != lineItem) {
                    setTextViews(lineItem);
                }
            }
        });
    }

    public void updateLineItem(View view) {
        lineItemViewModel.updateLineItem(new LineItem(
                getDateValue().getTime(),
                descView.getText().toString(),
                Double.parseDouble(amountView.getText().toString()),
                categoryView.getText().toString()
        ));
    }

    public void showDatePicker(View v) {
        DialogFragment fragment = new DatePickerFragment();
        fragment.show(getFragmentManager(), TAG + "datePicker");
    }

    private void setTextViews(@NonNull LineItem lineItem) {
        Date date = new Date(lineItem.getDate());
        SimpleDateFormat format = new SimpleDateFormat("mm/dd/yy", Locale.ENGLISH);
        dateView.setText(format.format(date));
        descView.setText(lineItem.getDescription());
        amountView.setText(String.format(
                Locale.ENGLISH, StringFormats.AMOUNT, lineItem.getAmount()));
        categoryView.setText(lineItem.getCategory());
    }

    private Date getDateValue() {
        Date date = null;
        String dateString = dateView.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yy", Locale.ENGLISH);
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            Log.d(TAG, e.getMessage());
        }
        return date;
    }
}
