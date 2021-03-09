package hu.okki.pilldroid.screens.medlist

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.model.Medication

class MedListViewModel : ViewModel() {

    var medList = hu.okki.pilldroid.data.medList

    fun newMedication(): Medication {
        val medication = Medication("New Medication")
        medList.add(medication)
        return medication
    }

}