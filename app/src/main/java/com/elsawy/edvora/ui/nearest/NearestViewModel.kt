package com.elsawy.edvora.ui.nearest

import androidx.lifecycle.ViewModel
import com.elsawy.edvora.data.network.RidesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NearestViewModel @Inject constructor(
   private val ridesRepository: RidesRepository,
) : ViewModel() {
   val nearestRides = ridesRepository.nearestRides
   val stationCode = ridesRepository.stationCode
}