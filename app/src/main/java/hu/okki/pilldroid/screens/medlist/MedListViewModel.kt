package hu.okki.pilldroid.screens.medlist

import androidx.databinding.ObservableList
import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.medicationRepository
import hu.okki.pilldroid.model.Medication
import hu.okki.pilldroid.repository.MedicationRepository

class MedListViewModel : ViewModel() {

    lateinit var medList: List<Medication>

    fun addMedication(): Medication {
        return medicationRepository.newMedication()
    }

}