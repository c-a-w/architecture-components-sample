package com.mainuser.budgetapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class LineItemDaoTest {

    private LineItemDatabase lineItemDatabase;
    private LineItemDao lineItemDao;
    private LineItem lineItem;

    private static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }

    @Before
    public void setUp() throws Exception {
        lineItemDatabase = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(), LineItemDatabase.class).build();
        lineItemDao = lineItemDatabase.lineItemDao();

        Date date = new Date();
        lineItem = new LineItem(
                convertCalendar(date),
                "Shop",
                25.72,
                "S"
        );
        lineItemDao.insert(lineItem);
    }

    @After
    public void tearDown() throws Exception {
        lineItemDatabase.close();
    }

    @Test
    public void findById() throws Exception {
        LiveData<LineItem> firstLineItem = lineItemDao.findById(1);
        assertThat(getValue(firstLineItem).getDescription(), is("Shop"));
    }

    @Test
    public void findAll() throws Exception {
        assertThat(lineItemDao.findAll(), is(notNullValue()));
        List<LineItem> list = getValue(lineItemDao.findAll());
        assertThat(list, is(notNullValue()));

        LineItem lineItem2 = new LineItem(
                convertCalendar(new Date()),
                "Cafe",
                25.00,
                "R"
        );
        lineItemDao.insert(lineItem2);
        assertThat(getValue(lineItemDao.findAll()), hasSize(2));
    }

    @Test
    public void insertCreatesLineItem() throws Exception {
        int listSize = lineItemDao.findAll().getValue().size();
        assertThat(listSize, is(greaterThan(0)));
        LineItem lineItem = new LineItem(
                convertCalendar(new Date()),
                "Store",
                55.00,
                "S"
        );
        lineItemDao.insert(lineItem);
        assertThat(lineItemDao.findAll().getValue().size(), is(greaterThan(listSize)));
    }

    @Test
    public void delete() throws Exception {
        assertThat(getValue(lineItemDao.findAll()), hasSize(1));

        LineItem lineItem2 = new LineItem(
                convertCalendar(new Date()),
                "Cafe",
                25.00,
                "R"
        );
        lineItemDao.insert(lineItem2);
        assertThat(getValue(lineItemDao.findAll()), hasSize(2));

        lineItemDao.delete(getValue(lineItemDao.findById(1)));
        assertThat(getValue(lineItemDao.findAll()), hasSize(1));
        assertThat(getValue(lineItemDao.findById(1)), is(nullValue()));
        assertThat(getValue(lineItemDao.findById(2)).getCategory(), is("R"));
    }

    private long convertCalendar(Date date) {
        return date.getTime();
    }
}