package com.mainuser.budgetapp.database;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

public class LineItemRepository {
    private static final String TAG = LineItemRepository.class.getSimpleName();

    private LineItemDao lineItemDao;

    public LineItemRepository(LineItemDao lineItemDao) {
        this.lineItemDao = lineItemDao;
    }

    public LiveData<List<LineItem>> getLineItems() {
        return lineItemDao.findAll();
    }

    public LiveData<LineItem> getLineItem(int id) {
        return lineItemDao.findById(id);
    }

    public void updateLineItem(LineItem lineItem) {
        Log.d(TAG, lineItem.toString());
        lineItemDao.insert(lineItem);
    }

    public void deleteLineItem(LineItem lineItem) {
        lineItemDao.delete(lineItem);
    }
}
