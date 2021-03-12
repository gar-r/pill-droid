package hu.okki.pilldroid.alarm

import android.app.AlarmManager
import android.app.AlarmManager.RTC_WAKEUP
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_NO_CREATE
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.net.Uri
import hu.okki.pilldroid.data.medList
import hu.okki.pilldroid.data.medListOld
import hu.okki.pilldroid.model.Dosage
import java.util.*

fun updateAlarms(context: Context) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    val new = medList.flatMap { it.dosages }
    val old = medListOld.flatMap { it.dosages }
    old.filter { findById(new, it) == null }.forEach { cancelAlarm(alarmManager, context, it) }
    new.forEach {
        val previous = findById(old, it)
        if (previous == null) {
            setAlarm(alarmManager, context, it)
        }
        else if (needsUpdate(previous, it)) {
            cancelAlarm(alarmManager, context, it)
            setAlarm(alarmManager, context, it)
        }
    }
}

fun reschedule(context: Context, dosage: Dosage) {
    val alarmManager = context.getSystemService(ALARM_SERVICE) as AlarmManager
    setAlarm(alarmManager, context, dosage)
}

private fun setAlarm(alarmManager: AlarmManager, context: Context, dosage: Dosage) {
    val intent = getIntent(context, dosage)
    val pendingIntent = PendingIntent.getBroadcast(context, dosage.id, intent, 0)
    alarmManager.setExactAndAllowWhileIdle(
        RTC_WAKEUP,
        getAlarmTime(dosage),
        pendingIntent
    )
}

private fun cancelAlarm(alarmManager: AlarmManager, context: Context, dosage: Dosage) {
    val intent = getIntent(context, dosage)
    val pendingIntent = PendingIntent.getBroadcast(context, dosage.id, intent, FLAG_NO_CREATE)
    if (pendingIntent != null) {
        alarmManager.cancel(pendingIntent)
    }
}

private fun getIntent(context: Context, dosage: Dosage): Intent {
    val intent = Intent(context, AlarmReceiver::class.java)
    intent.data = Uri.parse("pilldroid://dosages/${dosage.id}")
    return intent
}

private fun getAlarmTime(dosage: Dosage): Long {
    Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, dosage.hour)
        set(Calendar.MINUTE, dosage.minute)
        set(Calendar.SECOND, 0)
        if (isElapsed(this)) {
            add(Calendar.DAY_OF_MONTH, Integer.parseInt(dosage.frequency))
        }
        return timeInMillis
    }
}

private fun isElapsed(calendar: Calendar): Boolean {
    Calendar.getInstance().apply {
        return calendar.before(this)
    }
}

private fun needsUpdate(d1: Dosage, d2: Dosage): Boolean {
    return d1.hour != d2.hour || d1.minute != d2.minute
}

private fun findById(list: List<Dosage>, dosage: Dosage): Dosage? {
    return list.firstOrNull { d -> dosage.id == d.id }
}

