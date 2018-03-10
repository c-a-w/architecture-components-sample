package com.mainuser.budgetapp.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

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
                    context, LineItemDatabase.class, "main-database")
                    .addMigrations(FROM_1_2).build();
        }
        return INSTANCE;
    }
    public static void destroyDatabaseInstance() {
        INSTANCE = null;
    }

    private static final Migration FROM_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
        }
    };
}
