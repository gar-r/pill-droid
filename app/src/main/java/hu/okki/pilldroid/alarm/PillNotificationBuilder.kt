package hu.okki.pilldroid.alarm

import android.app.Notification
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import hu.okki.pilldroid.MainActivity
import hu.okki.pilldroid.R


fun getNotificationBuilder(context: Context, channel: PillNotificationChannel): NotificationCompat.Builder {
    return NotificationCompat.Builder(context, channel.channelId).apply {
        setDefaults(Notification.DEFAULT_ALL)
        setContentTitle(context.getString(R.string.pill_reminder))
        setSmallIcon(R.drawable.medication_24px)
        setContentIntent(getTapIntent(context))
        setGroup(channel.channelGroup)
        setAutoCancel(true)
        priority = NotificationCompat.PRIORITY_HIGH
    }
}

private fun getTapIntent(context: Context): PendingIntent? {
    val intent = Intent(context, MainActivity::class.java)
    return TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(intent)
        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }
}