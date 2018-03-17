package com.mainuser.budgetapp.database;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class LineItemRepository {
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
        lineItemDao.insert(lineItem);
    }

    public void deleteLineItem(LineItem lineItem) {
        lineItemDao.delete(lineItem);
    }
}
