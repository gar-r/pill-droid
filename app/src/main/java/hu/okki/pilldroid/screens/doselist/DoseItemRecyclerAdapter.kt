package hu.okki.pilldroid.screens.doselist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.okki.pilldroid.databinding.FragmentDoseListItemBinding
import hu.okki.pilldroid.model.Dosage

class DoseItemRecyclerAdapter
    : ListAdapter<Dosage, DoseItemRecyclerAdapter.DoseItemViewHolder>(DoseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoseItemViewHolder {
        return DoseItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: DoseItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class DoseItemViewHolder private constructor(private val binding: FragmentDoseListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Dosage) {
            binding.dosage = item
            binding.executePendingBindings()
            itemView.setOnClickListener {
                // TODO
            }
        }

        companion object {
            fun from(parent: ViewGroup): DoseItemViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = FragmentDoseListItemBinding.inflate(inflater, parent, false)
                return DoseItemViewHolder(binding)
            }
        }

    }

    class DoseDiffCallback : DiffUtil.ItemCallback<Dosage>() {

        override fun areItemsTheSame(oldItem: Dosage, newItem: Dosage): Boolean {
            return oldItem.frequency == newItem.frequency
                    && oldItem.amount == newItem.amount
                    && oldItem.hour == newItem.hour
                    && oldItem.minute == newItem.minute

        }

        override fun areContentsTheSame(oldItem: Dosage, newItem: Dosage): Boolean {
            return oldItem == newItem
        }

    }

}