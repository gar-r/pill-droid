package hu.okki.pilldroid.screens.meddetails

import android.content.Context
import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.R
import hu.okki.pilldroid.data.medList
import hu.okki.pilldroid.data.nextDoseId
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication
import java.util.*
import java.util.Calendar.HOUR_OF_DAY
import java.util.Calendar.MINUTE

class MedDetailsViewModel : ViewModel() {

    lateinit var medication: Medication

    fun addDosage(context: Context): Dosage {
        with (Calendar.getInstance()) {
            val dosage = Dosage(nextDoseId(), "1", get(HOUR_OF_DAY), get(MINUTE), context.getString(
                            R.string.one_pill))
            medication.dosages.add(dosage)
            return dosage
        }
    }

    fun deleteMedication() {
        medList.remove(medication)
    }

}