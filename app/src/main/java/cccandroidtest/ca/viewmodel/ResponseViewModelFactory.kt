package cccandroidtest.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cccandroidtest.ca.repository.EstimateRepository
import java.lang.IllegalArgumentException

class ResponseViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResponseViewModel::class.java)){
            return ResponseViewModel() as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}