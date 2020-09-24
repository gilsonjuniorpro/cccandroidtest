package cccandroidtest.ca.viewmodel

import androidx.lifecycle.*
import cccandroidtest.ca.model.Estimate
import cccandroidtest.ca.repository.EstimateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EstimateViewModel(
    private val repository: EstimateRepository
) : ViewModel() {
    private val _estimate = MutableLiveData<Estimate>()
    val estimate: LiveData<Estimate> = _estimate

    private val _list = MutableLiveData<List<Estimate>>()
    val listEstimate: LiveData<List<Estimate>> = _list

    val allEstimate = repository.getAll().asLiveData()

    fun insert(estimate: Estimate){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                repository.insert(estimate)
            }
        }
    }

    fun update(estimate: Estimate){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                repository.update(estimate)
            }
        }
    }

    fun delete(estimate: Estimate){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                repository.delete(estimate)
            }
        }
    }

    fun search() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _list.postValue(repository.load())
            }
        }
    }
/*
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
                RetrieveHttp.getFromTeamPlayerFromAPI()
            }

            if(result == null){
                _state.value = State.Error(Exception("Error loading team players"), false)
            }else{
                viewModelScope.launch {
                    result.forEach{
                        insertTeam(
                            EntityResponseMapper.responseItemToEntity(it)
                        )
                    }
                }
                _state.value = State.Loaded(result)
            }
        }
    }

    sealed class State {
        object Loading: State()
        data class Loaded(val items: Response): State()
        data class Error(val e: Throwable, var hasConsumed: Boolean): State()
    }*/
}