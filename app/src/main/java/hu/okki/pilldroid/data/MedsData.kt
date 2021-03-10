package hu.okki.pilldroid.data

import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication
import java.util.NoSuchElementException

var medList = mutableListOf<Medication>()

var medListOld = mutableListOf<Medication>()

fun nextMedId(): Int {
    if (medList.isEmpty()) {
        return 0
    }
    return medList.maxOf { m -> m.id } + 1
}

fun nextDoseId(): Int {
    val dosages = medList.flatMap { m -> m.dosages }
    if (dosages.isEmpty()) {
        return 0
    }
    return dosages.maxOf { d -> d.id } + 1
}

fun getMedById(id: Int): Medication {
    return medList.single { med -> med.id == id }
}

fun getDoseById(id: Int): Dosage {
    return medList.flatMap { med -> med.dosages }.single { d -> d.id == id }
}

fun getMedDataByDoseId(id: Int): Pair<Medication, Dosage>? {
    medList.forEach { med ->
        med.dosages.forEach { dose ->
            if (dose.id == id) {
                return Pair(med, dose)
            }
        }
    }
    return null
}
