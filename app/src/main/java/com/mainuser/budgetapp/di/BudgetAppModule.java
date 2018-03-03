package com.mainuser.budgetapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mainuser.budgetapp.ViewModelFactory;
import com.mainuser.budgetapp.database.LineItemDao;
import com.mainuser.budgetapp.database.LineItemDatabase;
import com.mainuser.budgetapp.database.LineItemRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BudgetAppModule {

    private Application application;

    public BudgetAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    LineItemDatabase provideMainDatabase() {
        return Room.databaseBuilder(application, LineItemDatabase.class, "main-database")
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    LineItemRepository provideLineItemRepository(LineItemDao lineItemDao) {
        return new LineItemRepository(lineItemDao);
    }

    @Provides
    @Singleton
    LineItemDao provideLineItemDao(LineItemDatabase database) {
        return  database.lineItemDao();
    }

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(LineItemRepository repository) {
        return new ViewModelFactory(application, repository);
    }

    @Provides
    @Singleton
    RecyclerView.LayoutManager provideRecyclerViewLayoutManager() {
        return new LinearLayoutManager(application);
    }
}
