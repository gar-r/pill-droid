package hu.okki.pilldroid.screens.medlist

import android.content.Context
import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.R
import hu.okki.pilldroid.helper.getString
import hu.okki.pilldroid.model.Medication
import hu.okki.pilldroid.repository.MedicationRepository

class MedListViewModel : ViewModel() {

    lateinit var medList: List<Medication>

    fun addMedication(context: Context): Medication {
        val name = getString(context, R.string.new_medication)
        return MedicationRepository.getInstance(context).newMedication(name)
    }

}