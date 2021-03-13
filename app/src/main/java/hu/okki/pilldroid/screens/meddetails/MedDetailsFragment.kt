package hu.okki.pilldroid.screens.meddetails

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hu.okki.pilldroid.R
import hu.okki.pilldroid.data.getMedById
import hu.okki.pilldroid.databinding.FragmentMedDetailsBinding
import hu.okki.pilldroid.screens.doselist.DoseItemRecyclerAdapter

class MedDetailsFragment : Fragment() {

    private lateinit var viewModel: MedDetailsViewModel
    private lateinit var doseItemRecyclerAdapter: DoseItemRecyclerAdapter

    private val args: MedDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentMedDetailsBinding>(
            inflater,
            R.layout.fragment_med_details,
            container,
            false)
        viewModel = ViewModelProvider(this).get(MedDetailsViewModel::class.java)
        viewModel.medication = getMedById(args.medicationId)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        bindList(binding.root)
        bindAddButton(binding.root)
        return binding.root
    }

    private fun bindList(v: View) {
        val recycler = v.findViewById<RecyclerView>(R.id.dose_list_recycler_view)
        doseItemRecyclerAdapter = DoseItemRecyclerAdapter()
        doseItemRecyclerAdapter.submitList(viewModel.medication.dosages)
        recycler.adapter = doseItemRecyclerAdapter
    }

    private fun bindAddButton(view: View) {
        val button = view.findViewById<FloatingActionButton>(R.id.addDosageButton)
        button.setOnClickListener { addNewDosage() }
    }

    private fun addNewDosage() {
        context?.let {
            val dosage = viewModel.addDosage(it)
            val action =
                MedDetailsFragmentDirections.actionMedDetailsFragmentToDoseDetailsFragment(dosage.id)
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_details, menu)
        inflater.inflate(R.menu.menu_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_add -> {
            addNewDosage()
            true
        }
        R.id.action_delete -> {
            val builder = AlertDialog.Builder(context)
                .setMessage("\"${viewModel.medication.name}\" ${getString(R.string.will_be_deleted)}")
                .setTitle(getString(R.string.please_confirm))
                .setPositiveButton(getString(R.string.delete)) { _, _ ->
                    viewModel.deleteMedication()
                    findNavController().popBackStack()
                }
                .setNegativeButton(getString(R.string.cancel)) { _, _ -> run {} }
            builder.create().show()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}