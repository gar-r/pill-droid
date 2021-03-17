package hu.okki.pilldroid.alarm

import android.content.Context
import android.util.Log
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.time.Duration

class AlarmMaintenanceWorker(private val appContext: Context, workerParameters: WorkerParameters)
    : Worker(appContext, workerParameters) {

    override fun doWork(): Result {
        scheduleNextAlarm(appContext)
        return Result.success()
    }

}

fun scheduleWorker(context: Context) {
    Log.d("alarm", "worker called")
    val tag = "pilldroid"
    val req = PeriodicWorkRequestBuilder<AlarmMaintenanceWorker>(
        Duration.ofMinutes(15), Duration.ofMinutes(5)
    ).addTag(tag).build()
    WorkManager.getInstance(context).cancelAllWorkByTag(tag)
    WorkManager.getInstance(context).enqueue(req)
    Log.d("alarm", "worker finished")
}