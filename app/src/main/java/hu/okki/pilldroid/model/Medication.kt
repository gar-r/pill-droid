package hu.okki.pilldroid.model

import java.io.Serializable
import java.util.*

@kotlinx.serialization.Serializable
data class Medication(
    val id: String,
    var name: String,
    val dosages: List<Dosage>
) : Serializable {

    constructor(name: String) : this(
        UUID.randomUUID().toString(),
        name,
        mutableListOf()
    )

    constructor(name: String, dosages: List<Dosage>): this(
        UUID.randomUUID().toString(),
        name,
        dosages
    )

}