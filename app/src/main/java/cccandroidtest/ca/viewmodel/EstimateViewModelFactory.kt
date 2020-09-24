package cccandroidtest.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cccandroidtest.ca.repository.EstimateRepository
import java.lang.IllegalArgumentException

class EstimateViewModelFactory(val repository: EstimateRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EstimateViewModel::class.java)){
            return EstimateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}