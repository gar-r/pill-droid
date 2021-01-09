package hu.okki.pilldroid.model

data class Dosage(
    private val frequency: Int,
    private val hour: Int,
    private val minute: Int,
    private val amount: String
)
