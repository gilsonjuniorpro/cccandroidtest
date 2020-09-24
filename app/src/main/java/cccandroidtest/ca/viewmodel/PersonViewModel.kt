package cccandroidtest.ca.viewmodel

import androidx.lifecycle.*
import cccandroidtest.ca.model.Person
import cccandroidtest.ca.repository.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonViewModel(
    private val repository: PersonRepository
) : ViewModel() {
    private val _person = MutableLiveData<Person>()
    val person: LiveData<Person> = _person

    private val _list = MutableLiveData<List<Person>>()
    val listPerson: LiveData<List<Person>> = _list

    val allPerson = repository.getAll().asLiveData()

    fun insert(person: Person){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                repository.insert(person)
            }
        }
    }

    fun update(person: Person){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                repository.update(person)
            }
        }
    }

    fun delete(person: Person){
        viewModelScope.launch{
            withContext(Dispatchers.IO){
                repository.delete(person)
            }
        }
    }

    fun load() {
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