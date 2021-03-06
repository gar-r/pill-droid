package hu.okki.pilldroid.data

import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication
import java.util.NoSuchElementException

var medList = mutableListOf<Medication>()

fun getMedDataByDoseId(id: String): Pair<Medication, Dosage> {
    medList.forEach { med ->
        med.dosages.forEach { dose ->
            if (dose.id == id) {
                return Pair(med, dose)
            }
        }
    }
    throw NoSuchElementException()
}
