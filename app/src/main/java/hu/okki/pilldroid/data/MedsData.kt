package hu.okki.pilldroid.data

import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication
import java.util.NoSuchElementException

var medList = mutableListOf<Medication>()

var medListOld = mutableListOf<Medication>()

fun getMedById(id: String): Medication {
    return medList.single { med -> med.id == id }
}

fun getDoseById(id: String): Dosage {
    return medList.flatMap { med -> med.dosages }.single { d -> d.id == id }
}

fun getMedDataByDoseId(id: String): Pair<Medication, Dosage>? {
    medList.forEach { med ->
        med.dosages.forEach { dose ->
            if (dose.id == id) {
                return Pair(med, dose)
            }
        }
    }
    return null
}
