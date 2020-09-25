package cccandroidtest.ca.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cccandroidtest.ca.model.Response
import cccandroidtest.ca.repository.EstimateRepository
import cccandroidtest.ca.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragmentViewModel(
    private val personRepository: PersonRepository,
    private val estimateRepository: EstimateRepository
) : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun load() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                var person = personRepository.load()
                var estimate = estimateRepository.load()

                var response = Response(
                    estimate,
                    person
                )
                _state.postValue(State.Loaded(response))
            }
        }
    }

    sealed class State {
        object Loading: State()
        data class Loaded(val item: Response): State()
        data class Error(val e: Throwable, var hasConsumed: Boolean): State()
    }
}