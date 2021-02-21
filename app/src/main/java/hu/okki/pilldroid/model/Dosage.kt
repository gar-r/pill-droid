package hu.okki.pilldroid.model

import java.io.Serializable

@kotlinx.serialization.Serializable
data class Dosage(
    var frequency: String,
    var hour: Int,
    var minute: Int,
    var amount: String
) : Serializable
