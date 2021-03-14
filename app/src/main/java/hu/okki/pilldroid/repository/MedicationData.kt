package hu.okki.pilldroid.repository

import hu.okki.pilldroid.model.Medication
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

interface MedicationData {
    fun saveMedications(medications: List<Medication>)
    fun loadMedications(): List<Medication>
}

class JsonFileMedicationData(private val filesDir: File) : MedicationData {

    private val medicationsFile = "medications.bin"

    private val json = Json { ignoreUnknownKeys = true }

    override fun saveMedications(medications: List<Medication>) {
        val f = File(filesDir, medicationsFile)
        f.delete()
        f.createNewFile()
        f.writeText(json.encodeToString(medications))
    }

    override fun loadMedications(): List<Medication> {
        val f = File(filesDir, medicationsFile)
        if (!f.exists()) {
            return emptyList()
        }
        val jsonStr = f.readText()
        return try {
            json.decodeFromString(jsonStr)
        } catch (e: Exception) {
            emptyList()
        }
    }

}