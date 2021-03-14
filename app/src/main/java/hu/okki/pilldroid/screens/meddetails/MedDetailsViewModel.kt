package hu.okki.pilldroid.screens.meddetails

import android.content.Context
import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.R
import hu.okki.pilldroid.helper.getString
import hu.okki.pilldroid.medicationRepository
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication
import hu.okki.pilldroid.repository.MedicationRepository

class MedDetailsViewModel : ViewModel() {

    lateinit var medication: Medication

    fun addDosage(context: Context): Dosage {
        val amount = getString(context, R.string.one_pill)
        return medicationRepository.newDosage(medication, "1", amount)
    }

    fun delete() {
        medicationRepository.deleteMedication(medication)
    }

}