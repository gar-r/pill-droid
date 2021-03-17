package hu.okki.pilldroid.alarm

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent.getBroadcast
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import hu.okki.pilldroid.repository.MedicationRepository

fun scheduleNextAlarm(context: Context) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    val receiveIntent = Intent(context, AlarmReceiver::class.java)
    val broadcast = getBroadcast(context, 0, receiveIntent, 0)
    val dosage = MedicationRepository.getInstance(context).findNextDose()
    when {
        dosage != null -> alarmManager.setExactAndAllowWhileIdle(
            RTC_WAKEUP,
            dosage.getNextEvent().timeInMillis,
            broadcast)
        else -> alarmManager.cancel(broadcast)
    }
}


