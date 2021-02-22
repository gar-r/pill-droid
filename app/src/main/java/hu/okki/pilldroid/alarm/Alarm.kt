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
import hu.okki.pilldroid.model.Dosage
import java.util.*

fun setAlarms(context: Context) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    forEachDose { d ->
        setAlarm(alarmManager, context, d)
    }
}

fun cancelAlarms(context: Context) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    forEachDose { d ->
        cancelAlarm(alarmManager, context, d)
    }
}

private fun setAlarm(alarmManager: AlarmManager, context: Context, dosage: Dosage) {
    val intent = getIntent(context, dosage)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
    Log.d("alarm", "set alarm: ${dosage.id}")
    alarmManager.setRepeating(
        RTC_WAKEUP,
        getAlarmTime(dosage),
        INTERVAL_DAY,
        pendingIntent
    )
}

private fun cancelAlarm(alarmManager: AlarmManager, context: Context, dosage: Dosage) {
    val intent = getIntent(context, dosage)
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, FLAG_NO_CREATE)
    if (pendingIntent != null) {
        Log.d("alarm", "cancel alarm: ${dosage.id}")
        alarmManager.cancel(pendingIntent)
    }
}

private fun getAlarmTime(dosage: Dosage): Long {
    val cal = Calendar.getInstance().apply {
        timeInMillis = System.currentTimeMillis()
        set(Calendar.HOUR_OF_DAY, dosage.hour)
        set(Calendar.MINUTE, dosage.minute)
    }
    return cal.timeInMillis
}

private fun getIntent(context: Context, dosage: Dosage): Intent {
    val intent = Intent(context, AlarmReceiver::class.java)
    intent.data = Uri.parse("pilldroid://${dosage.id}")
    return intent
}

private fun forEachDose(action: (Dosage) -> Unit) {
    medList.forEach { medication ->
        medication.dosages.forEach { dosage ->
            action(dosage)
        }
    }
}

