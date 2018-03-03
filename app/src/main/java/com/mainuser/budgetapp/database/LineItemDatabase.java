package com.mainuser.budgetapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import javax.inject.Singleton;

// TODO: remove exportSchema later
// TODO: remove allowMainThreadQueries later
@Singleton
@Database(version=2, entities = LineItem.class, exportSchema = false)
@TypeConverters(RoomTypeConverters.class)
public abstract class LineItemDatabase extends RoomDatabase {
    abstract public LineItemDao lineItemDao();
    private static LineItemDatabase INSTANCE;

    public static LineItemDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context, LineItemDatabase.class, "main-database").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyDatabaseInstance() {
        INSTANCE = null;
    }
}
