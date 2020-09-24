package cccandroidtest.ca

import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import cccandroidtest.ca.model.Estimate
import cccandroidtest.ca.model.Person
import cccandroidtest.ca.repository.AppDatabase
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val db = AppDatabase.getDatabase(appContext)
        val estimateDao = db.getEstimateDao()
        val personDao = db.getPersonDao()

        runBlocking {
            val estimate: Estimate = estimateDao.get()

            estimate.company?.let { Log.i("logTest", it) }

            val person: Person = personDao.get()

            person.first_name?.let { Log.i("logTest", it) }
        }
    }
}