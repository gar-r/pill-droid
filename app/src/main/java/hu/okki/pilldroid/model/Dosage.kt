package hu.okki.pilldroid.model

data class Dosage(
    val frequency: Int,
    val hour: Int,
    val minute: Int,
    val amount: String
)
