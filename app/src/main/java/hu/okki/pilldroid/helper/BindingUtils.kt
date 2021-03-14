package hu.okki.pilldroid.helper

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication

@BindingAdapter("dosePretty")
fun TextView.setDosePretty(item: Dosage) {
    text = toPrettyString(context, item)
}

@BindingAdapter("medicationName")
fun TextView.setMedicationName(item: Medication) {
    text = item.name
}