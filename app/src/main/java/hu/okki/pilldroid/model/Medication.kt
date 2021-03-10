package hu.okki.pilldroid.model

import java.io.Serializable
import java.util.*

@kotlinx.serialization.Serializable
data class Medication(
    var id: Int,
    var name: String,
    val dosages: MutableList<Dosage>
) : Serializable {

    constructor(id: Int, name: String) : this(
        id,
        name,
        mutableListOf()
    )

}