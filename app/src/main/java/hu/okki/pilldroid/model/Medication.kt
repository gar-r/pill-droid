package hu.okki.pilldroid.model

import java.io.Serializable
import java.util.*

@kotlinx.serialization.Serializable
data class Medication(
    var id: String,
    var name: String,
    val dosages: MutableList<Dosage>
) : Serializable {

    constructor(name: String) : this(
        UUID.randomUUID().toString(),
        name,
        mutableListOf()
    )

    fun addDosage(dosage: Dosage) {
        dosages.add(dosage)
    }

}