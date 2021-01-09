package hu.okki.pilldroid.screens.reminders

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hu.okki.pilldroid.R
import hu.okki.pilldroid.databinding.RemindersFragmentBinding

class RemindersFragment : Fragment() {

    private lateinit var viewModel: RemindersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(RemindersViewModel::class.java)
        val binding = DataBindingUtil.inflate<RemindersFragmentBinding>(
            inflater, R.layout.reminders_fragment, container, false)
        binding.remindersViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    companion object {
        fun newInstance() = RemindersFragment()
    }

}
