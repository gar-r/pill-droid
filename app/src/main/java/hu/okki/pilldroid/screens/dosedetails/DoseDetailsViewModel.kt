package hu.okki.pilldroid.screens.dosedetails

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.repository.MedicationRepository

class DoseDetailsViewModel : ViewModel() {

    private lateinit var medicationRepository: MedicationRepository

    lateinit var dose : Dosage

    fun deleteDosage() {
        medicationRepository.deleteDosage(dose)
    }

}