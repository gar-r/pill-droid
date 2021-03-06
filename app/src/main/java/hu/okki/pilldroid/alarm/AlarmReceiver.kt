package hu.okki.pilldroid.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_HIGH
import hu.okki.pilldroid.R
import hu.okki.pilldroid.data.getMedDataByDoseId
import java.util.*

const val NOTIFICATION_CHANNEL_ID = "pilldroid-notification"
const val NOTIFICATION_NAME = "PillDroid Reminder"

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val dosageId = intent?.data?.lastPathSegment.toString()
        sendNotification(context, dosageId)
    }

    private fun sendNotification(context: Context?, dosageId: String) {
        context?.let {
            createNotificationChannel(it)
            val builder = NotificationCompat.Builder(it, NOTIFICATION_CHANNEL_ID).apply {
                setContentTitle(context.getString(R.string.pill_reminder))
                setContentText(getNotificationContent(context, dosageId))
                setSmallIcon(R.drawable.add_24px)
                priority = PRIORITY_HIGH
            }
            val notificationService =
                it.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationService.notify(1, builder.build())
        }
    }

    private fun getNotificationContent(context: Context, dosageId: String): String {
        val p = getMedDataByDoseId(dosageId)
        val medication = p.first
        val dosage = p.second
        val template = context.getString(R.string.notification_template)
        return String.format(template, dosage.amount, medication.name)
    }

    private fun createNotificationChannel(context: Context) {
        val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID, NOTIFICATION_NAME , NotificationManager.IMPORTANCE_HIGH).apply {
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
            description = "Test Notification"
        }
        notificationManager.createNotificationChannel(channel)
    }
}