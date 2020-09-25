package cccandroidtest.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cccandroidtest.ca.repository.EstimateRepository
import cccandroidtest.ca.repository.PersonRepository
import java.lang.IllegalArgumentException

class MainFragmentViewModelFactory(
    private val personRepository: PersonRepository,
    private val estimateRepository: EstimateRepository
):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainFragmentViewModel::class.java)){
            return MainFragmentViewModel(personRepository, estimateRepository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}