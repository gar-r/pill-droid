package hu.okki.pilldroid.screens.dosedetails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
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
        bindTimePicker(binding.root)
        return binding.root
    }

    private fun bindTimePicker(view: View) {
        val timePicker = view.findViewById<TimePicker>(R.id.dose_time_editor)
        timePicker.setIs24HourView(true)
        timePicker.hour = viewModel.dose.hour
        timePicker.minute = viewModel.dose.minute
        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            viewModel.dose.hour = hourOfDay
            viewModel.dose.minute = minute
        }
    }

}