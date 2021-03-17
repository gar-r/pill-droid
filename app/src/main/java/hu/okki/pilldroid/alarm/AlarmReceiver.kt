package hu.okki.pilldroid.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import hu.okki.pilldroid.repository.MedicationRepository
import java.util.*


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.run {
            MedicationRepository.getInstance(context)
                .findCurrentDoses(Calendar.getInstance())
                .forEach {
                    PillNotification(it).send(this)
                }
            scheduleNextAlarm(context)
        }
    }
}