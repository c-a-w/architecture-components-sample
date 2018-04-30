package com.mainuser.budgetapp;

import android.support.v7.widget.RecyclerView;

import com.mainuser.budgetapp.list.ItemsListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.verify;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

//@RunWith(MockitoJUnitRunner.class)
public class ItemsListActivityTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private ItemsListActivity activity;

    @Mock
    private RecyclerView recyclerView;

}