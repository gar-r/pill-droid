package hu.okki.pilldroid.data

import hu.okki.pilldroid.model.Medication
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

const val medicationsFile = "medications.bin"

fun saveMedList(filesDir: File) {
    val f = File(filesDir, medicationsFile)
    f.delete()
    f.createNewFile()
    f.writeText(Json.encodeToString(medList))
}

fun loadMedList(filesDir: File) {
    val f = File(filesDir, medicationsFile)
    if (!f.exists()) {
        return
    }
    val json = f.readText()
    Json.decodeFromString<List<Medication>>(json).forEach(medList::add)
}