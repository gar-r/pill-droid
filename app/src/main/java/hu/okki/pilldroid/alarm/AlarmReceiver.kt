package hu.okki.pilldroid.alarm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_HIGH
import hu.okki.pilldroid.MainActivity
import hu.okki.pilldroid.R
import hu.okki.pilldroid.data.getMedDataByDoseId
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.model.Medication

const val NOTIFICATION_CHANNEL_ID = "pilldroid-notification"
const val NOTIFICATION_NAME = "PillDroid Reminder"

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val idStr = intent!!.data!!.lastPathSegment!!
        val dosageId = Integer.parseInt(idStr)
        sendNotification(context, dosageId)
    }

    private fun sendNotification(context: Context?, dosageId: Int) {
        val medData = getMedDataByDoseId(dosageId)
        if (medData == null || context == null)
            return

        createNotificationChannel(context)
        val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID).apply {
            setContentTitle(context.getString(R.string.pill_reminder))
            setContentText(getNotificationContent(context, medData))
            setSmallIcon(R.drawable.medication_24px)
            setContentIntent(getTapIntent(context))
            priority = PRIORITY_HIGH
        }
        val notificationService =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationService.notify(medData.second.id, builder.build())
    }

    private fun getTapIntent(context: Context): PendingIntent? {
        val intent = Intent(context, MainActivity::class.java)
        return TaskStackBuilder.create(context).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
    }

    private fun getNotificationContent(context: Context, medData: Pair<Medication, Dosage>): String {
        val medication = medData.first
        val dosage = medData.second
        val template = context.getString(R.string.notification_template)
        return String.format(template, dosage.amount, medication.name)
    }

    private fun createNotificationChannel(context: Context) {
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
            description = "PillDroid Notification"
        }
        notificationManager.createNotificationChannel(channel)
    }
}