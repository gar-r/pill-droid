package hu.okki.pilldroid.screens.meddetails

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.medicationRepository
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication
import hu.okki.pilldroid.repository.MedicationRepository

class MedDetailsViewModel : ViewModel() {

    lateinit var medication: Medication

    fun addDosage(): Dosage {
        return medicationRepository.newDosage(medication)
    }

    fun delete() {
        medicationRepository.deleteMedication(medication)
    }

}