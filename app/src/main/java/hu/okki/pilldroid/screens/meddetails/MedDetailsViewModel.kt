package hu.okki.pilldroid.screens.meddetails

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.model.Medication

class MedDetailsViewModel : ViewModel() {

    lateinit var medication: Medication

    fun save(name: String) {
        medication.name = name
    }

}