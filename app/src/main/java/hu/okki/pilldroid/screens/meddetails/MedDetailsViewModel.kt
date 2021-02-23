package hu.okki.pilldroid.screens.meddetails

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.data.medList
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication
import java.util.*
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MINUTE

class MedDetailsViewModel : ViewModel() {

    lateinit var medication: Medication

    fun addDosage(): Dosage {
        with (Calendar.getInstance()) {
            val dosage = Dosage("1", get(HOUR_OF_DAY), get(MINUTE), "1 pill")
            medication.dosages.add(dosage)
            return dosage
        }
    }

    fun deleteMedication() {
        medList.remove(medication)
    }

}