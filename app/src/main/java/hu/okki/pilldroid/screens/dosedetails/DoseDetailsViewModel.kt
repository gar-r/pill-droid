package hu.okki.pilldroid.screens.dosedetails

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.data.medList
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication

class DoseDetailsViewModel : ViewModel() {

    lateinit var dose : Dosage

    fun deleteDosage() {
        val medication = findParent(dose)
        medication.dosages.remove(dose)
    }

    private fun findParent(dose: Dosage): Medication {
        return medList.single { med ->
            med.dosages.contains(dose)
        }
    }

}