package hu.okki.pilldroid.data

import android.util.Log
import hu.okki.pilldroid.model.Medication
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.lang.Exception

const val medicationsFile = "medications.bin"

val json = Json { ignoreUnknownKeys = true }

fun saveMedList(filesDir: File) {
    val f = File(filesDir, medicationsFile)
    f.delete()
    f.createNewFile()
    f.writeText(json.encodeToString(medList))
}

fun loadMedList(filesDir: File) {
    val f = File(filesDir, medicationsFile)
    if (!f.exists()) {
        return
    }
    val jsonStr = f.readText()
    try {
        json.decodeFromString<List<Medication>>(jsonStr).forEach(medList::add)
    }
    catch(e: Exception) {
        Log.e("persistence", e.toString())
    }




}