package com.mainuser.budgetapp.database;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class RoomTypeConverters {
    @TypeConverter
    public Long fromDateToLong(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public Date fromLongToDate(Long date) {
        return date == null ? null : new Date(date);
    }

}
