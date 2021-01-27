package hu.okki.pilldroid.screens.medlist

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.dummy.dummyMedList
import hu.okki.pilldroid.model.Medication

class MedListViewModel : ViewModel() {

    val medList = dummyMedList

    fun addMedication(): Medication {
        val medication = Medication("New Medication")
        medList.add(medication)
        return medication
    }

}