package com.elsawy.edvora.ui.upcoming

import androidx.lifecycle.ViewModel
import com.elsawy.edvora.data.network.RidesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
   private val ridesRepository: RidesRepository,
) : ViewModel() {
   val upcomingRides = ridesRepository.upcomingRides
   val stationCode = ridesRepository.stationCode
}