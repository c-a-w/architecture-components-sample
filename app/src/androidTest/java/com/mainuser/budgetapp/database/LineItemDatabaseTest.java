package com.mainuser.budgetapp.database;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class LineItemDatabaseTest {

    LineItemDatabase db;
    LineItemDao dao;

    @Mock
    Context context;

    @Rule public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Before
    public void setUp() throws Exception {
        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getContext(), LineItemDatabase.class).build();
        dao = db.lineItemDao();

    }

    @Test
    public void getDatabase() throws Exception {
        LineItemDatabase dbToTest = LineItemDatabase.getDatabase(context);
        assertThat(dbToTest, is(instanceOf(LineItemDatabase.class)));
    }

    @Test
    public void destroyDatabaseInstance() throws Exception {
    }

}