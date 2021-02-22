package hu.okki.pilldroid.model

import java.io.Serializable
import java.util.*

@kotlinx.serialization.Serializable
data class Dosage(
    var id: String,
    var frequency: String,
    var hour: Int,
    var minute: Int,
    var amount: String
) : Serializable {

    constructor(frequency: String, hour: Int, minute: Int, amount: String) : this(
        UUID.randomUUID().toString(),
        frequency,
        hour,
        minute,
        amount
    )

}