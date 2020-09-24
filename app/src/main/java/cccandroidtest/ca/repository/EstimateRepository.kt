package cccandroidtest.ca.repository

import android.content.Context
import cccandroidtest.ca.model.Estimate
import kotlinx.coroutines.flow.Flow

class EstimateRepository(context: Context) {

    private val estimateDao =
        AppDatabase.getDatabase(context).getEstimateDao()

    suspend fun insert(estimate: Estimate){
        estimateDao.insert(estimate)
    }

    suspend fun update(estimate: Estimate){
        estimateDao.update(estimate)
    }

    suspend fun delete(estimate: Estimate){
        estimateDao.delete(estimate)
    }

    fun getAll(): Flow<List<Estimate>> {
        return estimateDao.getAll()
    }

    fun search(keyword: String?): List<Estimate> {
        return if(keyword.isNullOrBlank()){
            estimateDao.search()
        }else{
            estimateDao.search(keyword)
        }
    }
}