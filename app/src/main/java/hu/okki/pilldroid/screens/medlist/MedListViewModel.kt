package hu.okki.pilldroid.screens.medlist

import android.content.Context
import androidx.lifecycle.ViewModel
import hu.okki.pilldroid.R
import hu.okki.pilldroid.data.nextMedId
import hu.okki.pilldroid.model.Medication

class MedListViewModel : ViewModel() {

    var medList = hu.okki.pilldroid.data.medList

    fun addMedication(context: Context): Medication {
        val medication = Medication(nextMedId(), context.getString(R.string.new_medication))
        medList.add(medication)
        return medication
    }

}