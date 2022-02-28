package com.elsawy.edvora.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elsawy.edvora.data.Status
import com.elsawy.edvora.data.local.PreferencesRepository
import com.elsawy.edvora.data.model.User
import com.elsawy.edvora.data.network.RidesRepository
import com.elsawy.edvora.data.network.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
   private val userRepository: UserRepository,
   private val ridesRepository: RidesRepository,
   private val PreferencesRepository: PreferencesRepository,
) : ViewModel() {

   private val _user = MutableStateFlow<User?>(null)
   val user = _user.asStateFlow()

   val tapsCount = ridesRepository.tapsCount

   init {
      getUser()
   }

   private fun getUser() {
      viewModelScope.launch(Dispatchers.IO) {
         val userResponse = userRepository.getUserData()
         if (userResponse.status == Status.SUCCESS)
            _user.value = userResponse.data
      }
   }

   fun updateRides(stationCode: Int) {
      val stateFilter = PreferencesRepository.stateFlow.value
      val cityFilter = PreferencesRepository.cityFlow.value
      viewModelScope.launch(Dispatchers.IO) {
         ridesRepository.updateRides(stationCode, stateFilter, cityFilter)
      }
   }

}