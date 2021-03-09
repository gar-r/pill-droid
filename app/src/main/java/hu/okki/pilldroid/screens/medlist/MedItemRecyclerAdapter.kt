package hu.okki.pilldroid.screens.medlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.okki.pilldroid.databinding.FragmentMedListItemBinding
import hu.okki.pilldroid.model.Medication

class MedItemRecyclerAdapter
    : ListAdapter<Medication, MedItemRecyclerAdapter.MedItemViewHolder>(MedicationDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedItemViewHolder {
        return MedItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MedItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MedItemViewHolder private constructor(private val binding: FragmentMedListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Medication) {
            binding.medication = item
            binding.executePendingBindings()
            itemView.setOnClickListener {
                val action = MedListFragmentDirections.actionMedListFragmentToMedDetails(item.id)
                itemView.findNavController().navigate(action)
            }
        }

        companion object {
            fun from(parent: ViewGroup): MedItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = FragmentMedListItemBinding.inflate(inflater, parent, false)
                return MedItemViewHolder(binding)
            }
        }

    }

    class MedicationDiffCallback : DiffUtil.ItemCallback<Medication>() {

        override fun areItemsTheSame(oldItem: Medication, newItem: Medication): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Medication, newItem: Medication): Boolean {
            return oldItem == newItem
        }

    }

}

