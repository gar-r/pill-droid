package hu.okki.pilldroid.screens.medlist

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.data.nextMedId
import hu.okki.pilldroid.model.Medication

class MedListViewModel : ViewModel() {

    var medList = hu.okki.pilldroid.data.medList

    fun addMedication(): Medication {
        val medication = Medication(nextMedId(), "New Medication")
        medList.add(medication)
        return medication
    }

}