package hu.okki.pilldroid.screens.meddetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import hu.okki.pilldroid.R
import hu.okki.pilldroid.databinding.FragmentMedDetailsBinding
import hu.okki.pilldroid.model.Medication

class MedDetailsFragment : Fragment() {

    private lateinit var viewModel: MedDetailsViewModel
    private val args: MedDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMedDetailsBinding>(
            inflater,
            R.layout.fragment_med_details,
            container,
            false)
        viewModel = ViewModelProvider(this).get(MedDetailsViewModel::class.java)
        viewModel.medication = MutableLiveData(args.medication)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

}