package hu.okki.pilldroid.model

data class Medication(
    private val name: String,
    private val dosages: List<Dosage>
)