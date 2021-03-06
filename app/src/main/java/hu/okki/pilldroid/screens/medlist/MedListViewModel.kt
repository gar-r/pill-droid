package hu.okki.pilldroid.screens.medlist

import android.content.Intent
import android.os.SystemClock
import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.MainActivity
import hu.okki.pilldroid.alarm.AlarmReceiver
import hu.okki.pilldroid.model.Medication

class MedListViewModel : ViewModel() {

    val medList = hu.okki.pilldroid.data.medList

    fun addMedication(): Medication {
        val medication = Medication("New Medication")
        medList.add(medication)
        return medication
    }

}