package hu.okki.pilldroid.alarm

import android.app.NotificationManager
import android.content.Context
import hu.okki.pilldroid.R
import hu.okki.pilldroid.model.Dosage
import hu.okki.pilldroid.repository.MedicationRepository


class PillNotification(private val dosage: Dosage) {

    fun send(context: Context) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = PillNotificationChannel(notificationManager)
        channel.create()
        val builder = getNotificationBuilder(context, channel)
        builder.setContentText(getContent(context, dosage))
        notificationManager.notify(dosage.hashCode(), builder.build())
    }

    private fun getContent(context: Context, dosage: Dosage): String {
        val medication = MedicationRepository.getInstance(context).getParent(dosage)
        val template = context.getString(R.string.notification_template)
        return String.format(template, dosage.amount, medication.name)
    }

}