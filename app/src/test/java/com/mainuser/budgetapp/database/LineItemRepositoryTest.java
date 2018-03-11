package com.mainuser.budgetapp.database;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.verify;

public class LineItemRepositoryTest {

    LineItemRepository repo;
    LineItem lineItem;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS);

    @Mock
    private LineItemDao dao;

    @Before
    public void setUp() throws Exception {
        repo = new LineItemRepository(dao);
    }

    @Test
    public void getLineItems() throws Exception {
        repo.getLineItems();
        verify(dao).findAll();
    }

    @Test
    public void getLineItem() throws Exception {
        repo.getLineItem(1);
        verify(dao).findById(1);
    }

    @Test
    public void setLineItem() throws Exception {
        repo.updateLineItem(lineItem);
        verify(dao).insert(lineItem);
    }

    @Test
    public void deleteLineItem() throws Exception {
        repo.deleteLineItem(lineItem);
        verify(dao).delete(lineItem);
    }
}