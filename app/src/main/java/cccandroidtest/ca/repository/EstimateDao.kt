package cccandroidtest.ca.repository

import androidx.room.*
import cccandroidtest.ca.model.Estimate
import kotlinx.coroutines.flow.Flow

@Dao
interface EstimateDao {
    @Query("SELECT * FROM estimate ORDER BY id DESC")
    fun getAll(): Flow<List<Estimate>>

    @Query("SELECT * FROM estimate ORDER BY id DESC")
    fun get(): Estimate

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estimate: Estimate)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(estimate: Estimate)

    @Delete
    suspend fun delete(vararg estimate: Estimate)
}