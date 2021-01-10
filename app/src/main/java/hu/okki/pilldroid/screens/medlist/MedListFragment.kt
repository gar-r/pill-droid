package hu.okki.pilldroid.screens.medlist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import hu.okki.pilldroid.R
import hu.okki.pilldroid.databinding.FragmentMedListBinding

class MedListFragment : Fragment() {

    private lateinit var viewModel: MedListViewModel
    private lateinit var medItemRecyclerAdapter: MedItemRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(MedListViewModel::class.java)
        val binding = DataBindingUtil.inflate<FragmentMedListBinding>(
            inflater, R.layout.fragment_med_list, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val recycler = binding.root.findViewById<RecyclerView>(R.id.med_list_recycler_view)
        medItemRecyclerAdapter = MedItemRecyclerAdapter()
        medItemRecyclerAdapter.submitList(viewModel.medList)
        recycler.adapter = medItemRecyclerAdapter
        return binding.root
    }

}
