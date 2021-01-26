package hu.okki.pilldroid.model

import java.io.Serializable

data class Dosage(
    val frequency: Int,
    val hour: Int,
    val minute: Int,
    val amount: String
) : Serializable
