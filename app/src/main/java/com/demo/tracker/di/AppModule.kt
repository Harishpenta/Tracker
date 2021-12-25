package com.demo.tracker.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.tracker.utils.Constants.RUNNING_DATABASE_NAME
import com.demo.tracker.db.RunningDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // TODO : Creating an object of (Running Database) so it can b available in the whole Application which will handled by Dagger Hilt

    @Singleton
    @Provides
    fun provideRunningDatabase(@ApplicationContext appContext: Context) =
        Room.databaseBuilder(appContext, RunningDatabase::class.java, RUNNING_DATABASE_NAME).build()


    @Singleton
    @Provides
    fun provideRunDAO (db: RunningDatabase) = db.getRunDAO()
}