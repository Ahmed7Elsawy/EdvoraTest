package com.elsawy.edvora.ui.past

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.elsawy.edvora.data.model.Ride
import com.elsawy.edvora.databinding.FragmentPastBinding
import com.elsawy.edvora.ui.RideAdapter
import com.elsawy.edvora.ui.nearest.NearestViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PastFragment : Fragment() {
   private var _binding: FragmentPastBinding? = null
   private val binding get() = _binding!!
   private val adapter by lazy { RideAdapter() }

   private val viewModel: PastViewModel by viewModels()

   override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?,
   ): View? {
      _binding = FragmentPastBinding.inflate(inflater, container, false)

      binding.pastList.adapter = adapter

      lifecycleScope.launch {
         launch {
            viewModel.stationCode.collect {
               Log.d("StationCode Past", it.toString())
               adapter.updateStationCode(it)
            }
         }
         launch {
            viewModel.pastRides.collectLatest {
               adapter.submitList(it)
            }
         }
      }

      return binding.root
   }

   override fun onDestroyView() {
      super.onDestroyView()
      _binding = null
   }
}