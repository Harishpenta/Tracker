package com.demo.tracker.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.demo.tracker.R
import com.demo.tracker.ui.viewmodels.MainViewModel
import com.demo.tracker.ui.viewmodels.StatisticsViewModel
import dagger.hilt.android.AndroidEntryPoint

class StatisticsFragment : Fragment(R.layout.fragment_statistics) {
    private val viewModel : StatisticsViewModel by viewModels()
}