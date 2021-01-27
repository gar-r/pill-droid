package hu.okki.pilldroid.screens.meddetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hu.okki.pilldroid.R
import hu.okki.pilldroid.databinding.FragmentMedDetailsBinding

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
        viewModel.medication = args.medication
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }


}