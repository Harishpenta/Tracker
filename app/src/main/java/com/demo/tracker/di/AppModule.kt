package com.demo.tracker.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.demo.tracker.db.RunningDatabase
import com.demo.tracker.utils.Constants.KEY_FIRST_NAME
import com.demo.tracker.utils.Constants.KEY_FIRST_TIME_TOGGLE
import com.demo.tracker.utils.Constants.KEY_WEIGHT
import com.demo.tracker.utils.Constants.RUNNING_DATABASE_NAME
import com.demo.tracker.utils.Constants.SHARED_PREFERENCES_NAME
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
    fun provideRunDAO(db: RunningDatabase) = db.getRunDAO()


    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences) =
        sharedPref.getString(KEY_FIRST_NAME, "") ?: ""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences) =
        sharedPref.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences) =
        sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE, true)
}