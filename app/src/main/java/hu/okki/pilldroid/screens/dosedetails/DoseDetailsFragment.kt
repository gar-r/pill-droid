package hu.okki.pilldroid.screens.dosedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.DataBinderMapperImpl
import androidx.navigation.fragment.navArgs
import hu.okki.pilldroid.R
import hu.okki.pilldroid.databinding.FragmentDoseDetailsBinding

class DoseDetailsFragment : Fragment() {

    private lateinit var viewModel: DoseDetailsViewModel

    private val args: DoseDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentDoseDetailsBinding>(
            inflater,
            R.layout.fragment_dose_details,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(DoseDetailsViewModel::class.java)
        viewModel.dose = args.dose
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

}