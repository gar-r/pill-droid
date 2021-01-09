package hu.okki.pilldroid.screens.reminders

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RemindersViewModel : ViewModel() {

    private val _text = MutableLiveData<String>("test text")
    val text: LiveData<String> = _text

}