package hu.okki.pilldroid.screens.dosedetails

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.TimePicker
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import hu.okki.pilldroid.R
import hu.okki.pilldroid.databinding.FragmentDoseDetailsBinding
import hu.okki.pilldroid.helper.toPrettyString
import hu.okki.pilldroid.medicationRepository

class DoseDetailsFragment : Fragment() {

    private lateinit var viewModel: DoseDetailsViewModel

    private val args: DoseDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentDoseDetailsBinding>(
            inflater,
            R.layout.fragment_dose_details,
            container,
            false
        )
        viewModel = ViewModelProvider(this).get(DoseDetailsViewModel::class.java)
        viewModel.dose = medicationRepository.getDosageById(args.doseId)
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_details, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_delete -> {
            context?.let {
                val builder = AlertDialog.Builder(it)
                    .setMessage("\"${toPrettyString(it, viewModel.dose)}\" ${getString(R.string.will_be_deleted)}")
                    .setTitle(getString(R.string.please_confirm))
                    .setPositiveButton(getString(R.string.delete)) { _, _ ->
                        viewModel.deleteDosage()
                        findNavController().popBackStack()
                    }
                    .setNegativeButton(getString(R.string.cancel)) { _, _ -> run {} }
                builder.create().show()
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}