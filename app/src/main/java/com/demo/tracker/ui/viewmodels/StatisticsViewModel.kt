package com.demo.tracker.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.demo.tracker.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(private val mainRepository: MainRepository) :
    ViewModel() {
    val totalTimeRun = mainRepository.getTotalTimeInMillis()
    val totalAvgSpeed = mainRepository.getTotalAvgSpeed()
    val totalDistance = mainRepository.getTotalDistance()
    val totalCaloriesBurned = mainRepository.getTotalCaloriesBurned()

    val runsSortedByDate = mainRepository.getAllRunsSortedByDate()

}