package cccandroidtest.ca.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cccandroidtest.ca.repository.PersonRepository
import java.lang.IllegalArgumentException

class PersonViewModelFactory(val repository: PersonRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PersonViewModel::class.java)){
            return PersonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unkown ViewModel Class")
    }
}