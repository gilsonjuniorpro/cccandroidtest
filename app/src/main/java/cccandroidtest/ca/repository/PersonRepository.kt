package cccandroidtest.ca.repository

import android.content.Context
import cccandroidtest.ca.model.Person
import kotlinx.coroutines.flow.Flow

class PersonRepository(context: Context) {

    private val personDao =
        AppDatabase.getDatabase(context).getPersonDao()

    suspend fun insert(person: Person){
        personDao.insert(person)
    }

    suspend fun update(person: Person){
        personDao.update(person)
    }

    suspend fun delete(person: Person){
        personDao.delete(person)
    }

    fun getAll(): Flow<List<Person>> {
        return personDao.getAll()
    }

    fun search(keyword: String?): List<Person> {
        return if(keyword.isNullOrBlank()){
            personDao.search()
        }else{
            personDao.search(keyword)
        }
    }
}