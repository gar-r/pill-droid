package hu.okki.pilldroid.model

import java.io.Serializable

data class Dosage(
    var frequency: Int,
    var hour: Int,
    var minute: Int,
    var amount: String
) : Serializable
