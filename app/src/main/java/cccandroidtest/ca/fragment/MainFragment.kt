package cccandroidtest.ca.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cccandroidtest.ca.R
import cccandroidtest.ca.databinding.FragmentMainBinding
import cccandroidtest.ca.model.Response
import cccandroidtest.ca.repository.EstimateRepository
import cccandroidtest.ca.repository.PersonRepository
import cccandroidtest.ca.viewmodel.MainFragmentViewModel
import cccandroidtest.ca.viewmodel.MainFragmentViewModelFactory

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(
            this,
            MainFragmentViewModelFactory(
                PersonRepository(requireContext()),
                EstimateRepository(requireContext())
            )
        ).get(MainFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDataFromDatabase()
    }

    private fun loadDataFromDatabase(){
        viewModel.state.observe(requireActivity(), { state ->
            when (state) {
                is MainFragmentViewModel.State.Loading -> {

                }

                is MainFragmentViewModel.State.Loaded -> {
                    setBindData(state.item)
                }

                is MainFragmentViewModel.State.Error -> {
                    if (!state.hasConsumed) {
                        state.hasConsumed = true
                        Toast.makeText(
                            requireContext(), R.string.error_loading,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        })
        viewModel.load()
    }

    private fun setBindData(item: Response) {
        binding.tvCompanyName.text = item.estimate?.company
        binding.tvCompanyAddress.text = item.estimate?.address
        binding.tvEstNumber.text = item.estimate?.number.toString()
        binding.tvReviewNumber.text = item.estimate?.revision_number.toString()
        binding.tvCreated.text = item.estimate?.created_date
        binding.tvCreatedBy.text = "${item.person?.first_name} ${item.person?.last_name}"
        binding.tvRequester.text = "${item.person?.first_name} ${item.person?.last_name}"
        binding.tvContact.text = "${item.person?.email}\n${item.person?.phone_number}"
    }
}