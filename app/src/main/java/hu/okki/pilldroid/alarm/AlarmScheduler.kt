package hu.okki.pilldroid.alarm

import android.app.AlarmManager
import android.app.PendingIntent.getActivity
import android.app.PendingIntent.getBroadcast
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import hu.okki.pilldroid.MainActivity
import hu.okki.pilldroid.medicationRepository

fun scheduleNextAlarm(context: Context) {
    val receiveIntent = Intent(context, AlarmReceiver::class.java)
    val broadcast = getBroadcast(context, 0, receiveIntent, 0)
    val mainIntent = Intent(context, MainActivity::class.java)
    val activity = getActivity(context, 0, mainIntent, 0)
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    val dosage = medicationRepository.findNextDose()
    if (dosage != null) {
        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(dosage.getNextEvent().timeInMillis, activity), broadcast
        )
    } else {
        alarmManager.cancel(broadcast)
    }
}


