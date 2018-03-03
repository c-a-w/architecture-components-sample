package com.mainuser.budgetapp.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mainuser.budgetapp.BudgetApp;
import com.mainuser.budgetapp.R;
import com.mainuser.budgetapp.ViewModelFactory;
import com.mainuser.budgetapp.database.LineItem;
import com.mainuser.budgetapp.util.BaseActivity;
import com.mainuser.budgetapp.util.IntentActions;
import com.mainuser.budgetapp.util.StringFormats;

import java.util.Locale;

import javax.inject.Inject;

public class LineItemActivity extends BaseActivity {
    private static final String TAG = LineItemActivity.class.getSimpleName();

    @Inject
    ViewModelFactory factory;
    private int lineItemId;
    private LineItemViewModel lineItemViewModel;
    private boolean shouldUpdateExistingItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_item);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ((BudgetApp) getApplication()).getAppComponent().inject(this);

        lineItemViewModel = ViewModelProviders.of(this, factory).get(LineItemViewModel.class);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(IntentActions.LINE_ITEM_ID)) {
            lineItemId = intent.getIntExtra(IntentActions.LINE_ITEM_ID, -1);
        }
//        lineItemViewModel.getLineItemAt(lineItemId).observe(this, new Observer<LineItem>() {
//            @Override
//            public void onChanged(@Nullable LineItem lineItem) {
//                Log.d(TAG, "line item " + lineItemId + " updated. " + lineItem);
//                EditText descView = findViewById(R.id.input_desc);
//                EditText amountView = findViewById(R.id.input_amount);
//                EditText categoryView = findViewById(R.id.input_category);
//                if (null != lineItem) {
//                    descView.setText(lineItem.getDescription());
//                    amountView.setText(String.format(
//                            Locale.ENGLISH, StringFormats.AMOUNT, lineItem.getAmount()));
//                    categoryView.setText(lineItem.getCategory());
//                }
//            }
//        });
    }


//    public void updateLineItemInDatabase(View view) {
//        LiveData<LineItem> lineItemToUpdate;
//        if (shouldUpdateExistingItem) {
//            lineItemToUpdate = lineItemViewModel.getLineItemAt(lineItemId);
//        } else {
//            lineItemToUpdate = new LineItem();
//        }
//        lineItemToUpdate.setDate(getDateValue());
//        lineItemToUpdate.setDescription(getDescriptionValue());
//        lineItemToUpdate.setAmount(getAmountValue());
//        lineItemToUpdate.setCategory(getCategoryValue());
//
//        if (shouldUpdateExistingItem) {
//            mMainDatabase.lineItemDao().update(lineItemToUpdate);
//        } else {
//            mMainDatabase.lineItemDao().insert(lineItemToUpdate);
//        }
//        shouldUpdateExistingItem = false;
//    }
//
//    private Date getDateValue() {
//        Date date = null;
//        TextView dateText = findViewById(R.id.input_date);
//        String dateString = dateText.getText().toString();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy", Locale.ENGLISH);
//        try {
//            date = dateFormat.parse(dateString);
//        } catch (ParseException e) {
//            Log.d(TAG, e.getMessage());
//        }
//        return date;
//    }
//
//    public void showDatePicker(View v) {
//        DialogFragment fragment = new DatePickerFragment();
//        fragment.show(getFragmentManager(), TAG + "datePicker");
//    }
}
