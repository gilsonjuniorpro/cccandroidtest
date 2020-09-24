package cccandroidtest.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cccandroidtest.ca.repository.EstimateRepository
import cccandroidtest.ca.repository.PersonRepository
import java.lang.IllegalArgumentException

class ResponseViewModelFactory(
    private val personRepository: PersonRepository,
    private val estimateRepository: EstimateRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResponseViewModel::class.java)){
            return ResponseViewModel(personRepository, estimateRepository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}