package cccandroidtest.ca.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cccandroidtest.ca.R
import cccandroidtest.ca.model.Response
import cccandroidtest.ca.util.Constants
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private var response: Response? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            response = it.getParcelable(Constants.OBJECT_RESPONSE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvContact.text = response?.person?.first_name
    }
}