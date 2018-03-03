package com.mainuser.budgetapp.list;

import android.content.Context;
import android.view.ViewGroup;

import com.mainuser.budgetapp.database.LineItem;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isNull;

public class CustomListAdapterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    CustomListAdapter customListAdapter;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getItemCount() throws Exception {
        assertThat(customListAdapter.getItemCount(), is(notNullValue()));
    }

}