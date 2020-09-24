package cccandroidtest.ca.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cccandroidtest.ca.model.Estimate
import cccandroidtest.ca.model.Person

@Database(entities = [Person::class, Estimate::class], version = 1, exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun getEstimateDao(): EstimateDao
    abstract fun getPersonDao(): PersonDao

    companion object {
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase{
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "androidtestDb"
                ).build()
            }
            return instance!!
        }
    }
}