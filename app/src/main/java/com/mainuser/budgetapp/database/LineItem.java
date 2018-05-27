package com.mainuser.budgetapp.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Calendar;
import java.util.Date;

@Entity(tableName = "line_items")
public class LineItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    private long date;
    private String description;
    private Double amount;
    private String category;

    public LineItem(long date, String description, Double amount, String category) {
        this.date = date;
        this.description = description;
        this.amount = amount;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "LineItem, id: " + id
                + ", date: " + date
                + ", desc: " + description
                + ", amt: " + amount
                + ", category: " + category;
    }
}
