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

    fun toPrettyString(): String {
        return "$amount at ${formatTime(hour)}:${formatTime(minute)}, ${freqFormatted()}"
    }

    private fun freqFormatted(): String {
        return when (frequency) {
            "1" -> "every day"
            else -> "every $frequency days"
        }
    }

    private fun formatTime(t: Int): String {
        return when {
            t < 10 -> "0$t"
            else -> t.toString()
        }
    }


}