package com.mainuser.budgetapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.provider.CalendarContract;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LineItemRepository {
    private LineItemDao lineItemDao;

    public LineItemRepository(LineItemDao lineItemDao) {
        this.lineItemDao = lineItemDao;
    }

    public List<LineItem> getLineItems() {
        return lineItemDao.findAll();
    }

    public LineItem getLineItem(int id) {
        return lineItemDao.findById(id);
    }

    public void setLineItem(LineItem lineItem) {
        lineItemDao.insert(lineItem);
    }

    public void deleteLineItem(LineItem lineItem) {
        lineItemDao.delete(lineItem);
    }
}
