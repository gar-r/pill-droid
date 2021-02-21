package hu.okki.pilldroid.model

import java.io.Serializable
import java.util.*

@kotlinx.serialization.Serializable
data class Medication(
    var name: String,
    val dosages: MutableList<Dosage>
) : Serializable {

    constructor(name: String) : this(
        name,
        mutableListOf()
    )

}