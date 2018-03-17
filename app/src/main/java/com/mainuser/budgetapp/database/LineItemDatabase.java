package com.mainuser.budgetapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

// TODO: remove exportSchema later
@Database(version=1, entities = LineItem.class, exportSchema = false)
@TypeConverters(RoomTypeConverters.class)
public abstract class LineItemDatabase extends RoomDatabase {
    abstract public LineItemDao lineItemDao();
}
