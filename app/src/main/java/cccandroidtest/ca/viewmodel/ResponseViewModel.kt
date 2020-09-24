package cccandroidtest.ca.viewmodel

import androidx.lifecycle.*
import cccandroidtest.ca.model.Estimate
import cccandroidtest.ca.model.Response
import cccandroidtest.ca.network.RetrieveHttp
import cccandroidtest.ca.repository.EstimateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ResponseViewModel() : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun loadItems(){
        if (_state.value != null) return

        search()
    }

    fun search() {
        viewModelScope.launch {
            _state.value = State.Loading

            val result = withContext(Dispatchers.IO) {
                RetrieveHttp.getFromAPI()
            }

            if(result == null){
                _state.value = State.Error(Exception("Error loading data"), false)
            }else{
                /*viewModelScope.launch {
                    result.forEach{
                        insertTeam(
                            EntityResponseMapper.responseItemToEntity(it)
                        )
                    }
                }*/
                _state.value = State.Loaded(result)
            }
        }
    }

    sealed class State {
        object Loading: State()
        data class Loaded(val item: Response): State()
        data class Error(val e: Throwable, var hasConsumed: Boolean): State()
    }
}