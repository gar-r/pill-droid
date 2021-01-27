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
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import hu.okki.pilldroid.R
import hu.okki.pilldroid.databinding.FragmentMedDetailsBinding
import hu.okki.pilldroid.screens.doselist.DoseItemRecyclerAdapter

class MedDetailsFragment : Fragment() {

    private lateinit var viewModel: MedDetailsViewModel
    private lateinit var doseItemRecyclerAdapter: DoseItemRecyclerAdapter

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
        bindList(binding.root)
        return binding.root
    }

    private fun bindList(v: View) {
        val recycler = v.findViewById<RecyclerView>(R.id.dose_list_recycler_view)
        doseItemRecyclerAdapter = DoseItemRecyclerAdapter()
        doseItemRecyclerAdapter.submitList(viewModel.medication.dosages)
        recycler.adapter = doseItemRecyclerAdapter
    }


}