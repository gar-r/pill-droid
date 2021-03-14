package hu.okki.pilldroid.helper

import android.content.Context
import hu.okki.pilldroid.R
import hu.okki.pilldroid.model.Dosage


fun toPrettyString(context: Context, dosage: Dosage): String {
    with(dosage) {
        return "$amount ${
            getString(
                context,
                R.string.at
            )
        } ${formatTime(hour)}:${formatTime(minute)}, ${freqFormatted(context, dosage.frequency)}"
    }
}

private fun formatTime(t: Int): String {
    return when {
        t < 10 -> "0$t"
        else -> t.toString()
    }
}

private fun freqFormatted(context: Context, frequency: String): String {
    return when (frequency) {
        "1" -> "${getString(context, R.string.every)} ${getString(context, R.string.day)}"
        else -> "${getString(context, R.string.every)} $frequency ${
            getString(
                context,
                R.string.days
            )
        }"
    }
}

private fun getString(context: Context, id: Int) =
    context.resources.getString(id)

