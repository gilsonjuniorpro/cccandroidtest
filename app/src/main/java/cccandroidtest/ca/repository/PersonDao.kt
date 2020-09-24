package cccandroidtest.ca.repository

import androidx.room.*
import cccandroidtest.ca.model.Person
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {
    @Query("SELECT * FROM person ORDER BY id DESC")
    fun getAll(): Flow<List<Person>>

    @Query("SELECT * FROM person WHERE name LIKE '%' || :keyword || '%' " +
            " ORDER BY id DESC")
    fun search(keyword: String?): List<Person>

    @Query("SELECT * FROM person ORDER BY id DESC")
    fun search(): List<Person>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(person: Person)

    @Delete
    suspend fun delete(vararg person: Person)
}