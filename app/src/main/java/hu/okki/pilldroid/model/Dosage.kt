package hu.okki.pilldroid.model

import android.content.Context
import hu.okki.pilldroid.R
import java.io.Serializable
import java.util.*

@kotlinx.serialization.Serializable
data class Dosage(
    var id: Int,
    var frequency: String,
    var hour: Int,
    var minute: Int,
    var amount: String
) : Serializable {

    fun toPrettyString(context: Context): String {
        return "$amount ${getString(context, R.string.at)} ${formatTime(hour)}:${formatTime(minute)}, ${freqFormatted(context)}"
    }

    private fun freqFormatted(context: Context): String {
        return when (frequency) {
            "1" -> "${getString(context, R.string.every)} ${getString(context, R.string.day)}"
            else -> "${getString(context, R.string.every)} $frequency ${getString(context, R.string.days)}"
        }
    }

    private fun formatTime(t: Int): String {
        return when {
            t < 10 -> "0$t"
            else -> t.toString()
        }
    }

    private fun getString(context: Context, id: Int) =
        context.resources.getString(id)


}