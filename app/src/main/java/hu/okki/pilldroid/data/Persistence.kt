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
    Log.d("Persistence", "data saved: $medList")
}

fun loadMedList(filesDir: File) {
    val f = File(filesDir, medicationsFile)
    if (!f.exists()) {
        return
    }
    val jsonStr = f.readText()
    decode(jsonStr, medList)
    decode(jsonStr, medListOld)
    Log.d("Persistence", "data loaded: $jsonStr")
}

private fun decode(jsonStr: String, list: MutableList<Medication>) {
    list.clear()
    try {
        json.decodeFromString<List<Medication>>(jsonStr).forEach(list::add)
    } catch (e: Exception) {
        Log.e("persistence", e.toString())
    }
}