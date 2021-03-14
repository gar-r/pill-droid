package hu.okki.pilldroid.model

import java.io.Serializable
import java.util.*

@kotlinx.serialization.Serializable
data class Dosage(
    var id: String,
    var frequency: String,
    var hour: Int,
    var minute: Int,
    var amount: String
) : Serializable {

    constructor(frequency: String, hour: Int, minute: Int, amount: String)
            : this(UUID.randomUUID().toString(), frequency, hour, minute, amount)

    fun getNextEvent(): Calendar {
        val cal = toCalendar()
        val now = Calendar.getInstance()
        return when {
            now.before(cal) -> cal
            else -> cal.apply {
                cal.add(Calendar.DATE, Integer.parseInt(frequency))
            }
        }
    }

    fun toCalendar(): Calendar {
        return Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
        }
    }









}