package com.mainuser.budgetapp.di;

import android.app.Application;
import android.arch.persistence.room.Room;

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
    LineItemRepository provideLineItemRepository(LineItemDao lineItemDao) {
        return new LineItemRepository(lineItemDao);
    }

    @Provides
    @Singleton
    LineItemDao provideLineItemDao(LineItemDatabase db) {
        return db.lineItemDao();
    }

    @Provides
    @Singleton
    LineItemDatabase provideLineItemDatabase() {
        return Room.databaseBuilder(application, LineItemDatabase.class, "line-item-db")
                .allowMainThreadQueries().build();
    }

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(LineItemRepository repository) {
        return new ViewModelFactory(application, repository);
    }
}
