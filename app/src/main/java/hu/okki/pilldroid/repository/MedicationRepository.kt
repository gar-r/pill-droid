package hu.okki.pilldroid.repository

import android.content.Context
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication
import java.util.*

class MedicationRepository private constructor(private val medicationData: MedicationData) {

    private val medications: MutableList<Medication> =
        medicationData.loadMedications().toMutableList()

    fun saveAll() {
        medicationData.saveMedications(medications)
    }

    fun getAll(): MutableList<Medication> {
        return medications
    }

    fun newMedication(name: String): Medication {
        val medication = Medication(name)
        medications.add(medication)
        return medication
    }

    fun getMedicationById(id: String): Medication {
        return medications.single { it.id == id }
    }

    fun deleteMedication(medication: Medication) {
        medications.remove(medication)
    }

    fun newDosage(parent: Medication, frequency: String, amount: String): Dosage {
        with(Calendar.getInstance()) {
            val hour = get(Calendar.HOUR_OF_DAY)
            val minute = get(Calendar.MINUTE)
            val dosage = Dosage(frequency, hour, minute, amount)
            parent.addDosage(dosage)
            return dosage
        }
    }

    fun getDosageById(id: String): Dosage {
        return medications.flatMap { it.dosages }.single { it.id == id }
    }

    fun deleteDosage(dosage: Dosage) {
        getParent(dosage).dosages.remove(dosage)
    }

    fun getParent(dosage: Dosage): Medication {
        return medications.first { it.dosages.contains(dosage) }
    }

    fun findNextDose(): Dosage? {
        return medications
            .flatMap { it.dosages }
            .minByOrNull { it.getNextEvent() }
    }

    fun findCurrentDoses(calendar: Calendar): List<Dosage> {
        with(calendar) {
            val h = get(Calendar.HOUR_OF_DAY)
            val m = get(Calendar.MINUTE)
            return medications
                .flatMap { it.dosages }
                .filter { it.hour == h }
                .filter { it.minute == m }
        }
    }

    companion object {

        private var instance: MedicationRepository? = null

        fun getInstance(context: Context): MedicationRepository {
            if (instance == null) {
                val medicationData = JsonFileMedicationData(context.filesDir)
                instance = MedicationRepository(medicationData)
            }
            return instance as MedicationRepository
        }

    }

}