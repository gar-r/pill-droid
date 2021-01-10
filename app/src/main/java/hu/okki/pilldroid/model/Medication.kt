package hu.okki.pilldroid.model

import java.util.*

data class Medication(
    val id: UUID,
    val name: String,
    val dosages: List<Dosage>
)