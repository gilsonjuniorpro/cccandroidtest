package cccandroidtest.ca.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import cccandroidtest.ca.R
import cccandroidtest.ca.databinding.ActivityMainBinding
import cccandroidtest.ca.fragment.MainFragment
import cccandroidtest.ca.model.Response
import cccandroidtest.ca.util.Constants
import cccandroidtest.ca.util.NetworkConnection
import cccandroidtest.ca.util.Utils
import cccandroidtest.ca.viewmodel.ResponseViewModel
import cccandroidtest.ca.viewmodel.ResponseViewModelFactory
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: ResponseViewModel by lazy {
        ViewModelProvider(
            this,
            ResponseViewModelFactory()
        ).get(ResponseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupDataOrigins()
    }

    private fun setupDataOrigins(){
        val networkConnection = NetworkConnection(applicationContext)
        networkConnection.observe(this, { isConnected ->
            if (isConnected) {
                viewModel.loadItems()
            } else {
                loadInternalData()
            }
        })

        viewModel.state.observe(this, { state ->
            when (state) {
                is ResponseViewModel.State.Loading -> {
                    binding.loading.visibility = View.VISIBLE
                }

                is ResponseViewModel.State.Loaded -> {
                    binding.loading.visibility = View.GONE
                    openDetail(state.item)
                }

                is ResponseViewModel.State.Error -> {
                    binding.loading.visibility = View.GONE
                    if (!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(
                            this, R.string.error_loading,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
    }

    private fun loadInternalData(){
        val jsonFile: String? = Utils.getJsonDataFromAsset(this, "android_test.json")

        val response: Response = Gson().fromJson(jsonFile, Response::class.java)

        openDetail(response)
    }

    private fun openDetail(response: Response){
        val mainFragment = MainFragment()

        val bundle = Bundle()
        bundle.putParcelable(Constants.OBJECT_RESPONSE, response)
        mainFragment.arguments = bundle

        supportFragmentManager.beginTransaction().replace(R.id.container, mainFragment).commit()
    }
}