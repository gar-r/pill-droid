package hu.okki.pilldroid.screens.meddetails

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication

class MedDetailsViewModel : ViewModel() {

    lateinit var medication: Medication

    fun addDosage(): Dosage {
        val dosage = Dosage("1", 9, 0, "1 pill")
        medication.dosages.add(dosage)
        return dosage
    }

}