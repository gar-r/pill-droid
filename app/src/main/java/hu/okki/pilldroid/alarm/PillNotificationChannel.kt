package hu.okki.pilldroid.alarm

import android.app.NotificationChannel
import android.app.NotificationManager




class PillNotificationChannel(private val notificationManager: NotificationManager) {

    val channelId = "hu.okki.pilldroid.alarm.channel"
    val channelGroup = "hu.okki.pilldroid.alarm.group"

    private val notificationName = "PillDroid"

    fun create() {
        NotificationChannel(
            channelId,
            notificationName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "PillDroid"
            notificationManager.createNotificationChannel(this)
        }
    }

}