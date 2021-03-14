package hu.okki.pilldroid.screens.medlist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hu.okki.pilldroid.R
import hu.okki.pilldroid.databinding.FragmentMedListBinding
import hu.okki.pilldroid.medicationRepository
import hu.okki.pilldroid.model.Medication


class MedListFragment : Fragment() {

    private lateinit var viewModel: MedListViewModel
    private lateinit var medItemRecyclerAdapter: MedItemRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(MedListViewModel::class.java)
        viewModel.medList = medicationRepository.getAll()
        val binding = DataBindingUtil.inflate<FragmentMedListBinding>(
            inflater, R.layout.fragment_med_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        bindList(binding.root)
        bindNewButton(binding.root)
        return binding.root
    }

    private fun bindList(v: View) {
        val recycler = v.findViewById<RecyclerView>(R.id.med_list_recycler_view)
        medItemRecyclerAdapter = MedItemRecyclerAdapter()
        medItemRecyclerAdapter.submitList(viewModel.medList)
        recycler.adapter = medItemRecyclerAdapter
    }

    private fun bindNewButton(v: View) {
        val newButton = v.findViewById<FloatingActionButton>(R.id.newMedicationButton)
        newButton.setOnClickListener { addNewMedication() }
    }

    private fun addNewMedication() {
        context?.let {
            val medication = viewModel.addMedication(it)
            val action = MedListFragmentDirections.actionMedListFragmentToMedDetails(medication.id)
            findNavController().navigate(action)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add -> {
                addNewMedication()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
