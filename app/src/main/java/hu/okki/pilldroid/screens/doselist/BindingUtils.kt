package hu.okki.pilldroid.screens.doselist

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.okki.pilldroid.model.Dosage

@BindingAdapter("doseFrequency")
fun TextView.setDoseFrequency(item: Dosage) {
    text = item.frequency.toString()
}