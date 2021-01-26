package hu.okki.pilldroid.screens.meddetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.model.Medication

class MedDetailsViewModel : ViewModel() {

    lateinit var medication: LiveData<Medication>

}