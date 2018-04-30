package com.mainuser.budgetapp.list;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.isNull;

public class LineItemListAdapterTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    LineItemListAdapter lineItemListAdapter;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getItemCount() throws Exception {
        assertThat(lineItemListAdapter.getItemCount(), is(notNullValue()));
    }

}