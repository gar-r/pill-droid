package hu.okki.pilldroid.screens

import android.widget.TextView
import androidx.databinding.BindingAdapter
import hu.okki.pilldroid.model.Dosage

@BindingAdapter("dosePretty")
fun TextView.setDosePretty(item: Dosage) {
    text = item.toPrettyString(context)
}

