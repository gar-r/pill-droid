package hu.okki.pilldroid.screens

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.okki.pilldroid.model.Dosage

@BindingAdapter("doseFrequency")
fun TextView.setDoseFrequency(item: Dosage) {
    text = item.frequency.toString()
}

@BindingAdapter("dosePretty")
fun TextView.setDosePretty(item: Dosage) {
    text = "${item.amount} at ${formatTime(item.hour)}:${formatTime(item.minute)}, ${formatFreq(item.frequency)}"
}

private fun formatFreq(freq: Int): String {
    return when {
        freq == 1 -> "every day"
        else -> "every ${freq} days"
    }
}

private fun formatTime(t: Int): String {
    return when {
        t < 10 -> "0$t"
        else -> t.toString()
    }
}
