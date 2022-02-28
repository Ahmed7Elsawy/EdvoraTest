package com.elsawy.edvora.ui.past

import androidx.lifecycle.ViewModel
import com.elsawy.edvora.data.network.RidesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PastViewModel @Inject constructor(
   private val ridesRepository: RidesRepository,
) : ViewModel() {
   val pastRides = ridesRepository.pastRides
   val stationCode = ridesRepository.stationCode
}