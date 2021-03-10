package hu.okki.pilldroid.alarm

import android.app.AlarmManager
import android.app.AlarmManager.INTERVAL_DAY
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_NO_CREATE
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.net.Uri
import android.util.Log
import hu.okki.pilldroid.data.medList
import hu.okki.pilldroid.data.medListOld
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication
import java.lang.Integer.parseInt
import java.util.*
import java.util.concurrent.ThreadPoolExecutor

fun updateAlarms(context: Context) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager

    val dosages = medList.flatMap { med -> med.dosages }
    val oldDosages = medListOld.flatMap { med -> med.dosages }

    Log.d("Alarm", "current: $dosages")
    Log.d("Alarm", "old: $oldDosages")

    dosages.forEach { current ->
        val old = findById(oldDosages, current)
        if (old == null) {
            setAlarm(alarmManager, context, current)
        } else if (needsUpdate(current, old)) {
            cancelAlarm(alarmManager, context, old)
            setAlarm(alarmManager, context, current)
        }
    }

    oldDosages.forEach { old ->
        val current = findById(dosages, old)
        if (current == null) {
            cancelAlarm(alarmManager, context, old)
        }
    }
}

private fun findById(list: List<Dosage>, dosage: Dosage): Dosage? {
    return list.firstOrNull { d -> dosage.id == d.id }
}

private fun needsUpdate(d1: Dosage, d2: Dosage): Boolean {
    return d1.hour != d2.hour || d1.minute != d2.minute
}

private fun setAlarm(alarmManager: AlarmManager, context: Context, dosage: Dosage) {
    val intent = getIntent(context, dosage)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    Log.d("Alarm", "setting alarm: $dosage")
    alarmManager.setRepeating(
        RTC_WAKEUP,
        getAlarmTime(dosage),
         parseInt(dosage.frequency) * INTERVAL_DAY,
        pendingIntent
    )
}

private fun cancelAlarm(alarmManager: AlarmManager, context: Context, dosage: Dosage) {
    val intent = getIntent(context, dosage)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, FLAG_NO_CREATE)
    if (pendingIntent != null) {
        Log.d("Alarm", "cancelling alarm: $dosage")
        alarmManager.cancel(pendingIntent)
    }
}

private fun getAlarmTime(dosage: Dosage): Long {
    val cal = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, dosage.hour)
        set(Calendar.MINUTE, dosage.minute)
    }
    return cal.timeInMillis
}

private fun getIntent(context: Context, dosage: Dosage): Intent {
    val intent = Intent(context, AlarmReceiver::class.java)
    intent.data = Uri.parse("pilldroid://dosages/${dosage.id}")
    return intent
}

