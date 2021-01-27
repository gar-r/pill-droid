package hu.okki.pilldroid.model

import java.io.Serializable
import java.util.*

data class Medication(
    val id: UUID,
    var name: String,
    val dosages: List<Dosage>
) : Serializable {

    constructor(name: String) : this(
        UUID.randomUUID(),
        name,
        mutableListOf()
    )

}