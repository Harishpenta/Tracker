package com.demo.tracker.db

import androidx.lifecycle.LiveData
import androidx.room.*
import java.nio.channels.FileLock

@Dao
interface RunDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run) // :TODO insertion executes in coroutine

    @Delete
    suspend fun deleteRun(run: Run) // :TODO deletion executes in coroutine

    @Query("SELECT * FROM  running_table ORDER BY timeStamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>> // TODO : no need to make suspend fun bcoz getting data from Room DB will not execute in coroutine

    @Query("SELECT * FROM  running_table ORDER BY avgSpeedInKMH DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>> // TODO : no need to make suspend fun bcoz getting data from Room DB will not execute in coroutine

    @Query("SELECT * FROM  running_table ORDER BY distanceInMeters DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<Run>> // TODO : no need to make suspend fun bcoz getting data from Room DB will not execute in coroutine

    @Query("SELECT * FROM  running_table ORDER BY timeInMillisPersonRun DESC")
    fun getAllRunsSortedByTimeInMillis(): LiveData<List<Run>> // TODO : no need to make suspend fun bcoz getting data from Room DB will not execute in coroutine

    @Query("SELECT * FROM  running_table ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>> // TODO : no need to make suspend fun bcoz getting data from Room DB will not execute in coroutine

    @Query("SELECT SUM(timeStamp) FROM  running_table")
    fun getTotalTimeInMillis(): LiveData<Long> // TODO : no need to make suspend fun bcoz getting data from Room DB will not execute in coroutine

    @Query("SELECT SUM(caloriesBurned) FROM  running_table")
    fun getTotalCaloriesBurned(): LiveData<Int> // TODO : no need to make suspend fun bcoz getting data from Room DB will not execute in coroutine

    @Query("SELECT SUM(distanceInMeters) FROM  running_table")
    fun getTotalDistance(): LiveData<Int> // TODO : no need to make suspend fun bcoz getting data from Room DB will not execute in coroutine

    @Query("SELECT AVG(distanceInMeters) FROM  running_table")
    fun getTotalAvgSpeed(): LiveData<Float> // TODO : no need to make suspend fun bcoz getting data from Room DB will not execute in coroutine
}