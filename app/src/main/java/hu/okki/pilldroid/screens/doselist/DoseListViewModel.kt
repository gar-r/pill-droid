package hu.okki.pilldroid.screens.doselist

import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.model.Dosage

class DoseListViewModel : ViewModel() {

    lateinit var doseList: List<Dosage>

}