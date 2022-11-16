package com.example.madpractical11_20012021014_1

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
class Notes(var title: String , var subTitle: String,var Description:String ,var modifiedtime:String ,var reminderTime:Long,
            var isRemainderEnable:Boolean) {
    var remindertime:Long = System.currentTimeMillis()
    var id = noteIdGeneration()
    constructor(note:Notes) :
            this(note.title,note.subTitle,note.Description,note.modifiedtime,note.reminderTime,note.isRemainderEnable) {
        remindertime = note.remindertime
    }
    companion object {
        var idNote = 0
        fun noteIdGeneration(): Int {
            idNote++
            return idNote
        }

        var notesArray: List<Notes> = ArrayList()
        fun getCurrentDateTime(): String {
            val cal = Calendar.getInstance()
            val df: DateFormat = SimpleDateFormat("MMM, dd yyyy hh:mm:ss a")
            return df.format(cal.time)
        }

        fun getMillies(hour: Int, min: Int): Long {
            val setcalendar = Calendar.getInstance()
            setcalendar[Calendar.HOUR_OF_DAY] = hour
            setcalendar[Calendar.MINUTE] = min
            setcalendar[Calendar.SECOND] = 0
            return setcalendar.timeInMillis
        }

        fun setReminder(context: Context, cls: Class<*>?, note: Notes) {
            val intent = Intent(context, cls)
            val pendingIntent =
                PendingIntent.getBroadcast(
                    context, note.id, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
            val alarmManager =
                context.getSystemService(AppCompatActivity.ALARM_SERVICE) as
                        AlarmManager
            if (note.isRemainderEnable) {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    note.remindertime,
                    pendingIntent
                )
            } else
                alarmManager.cancel(pendingIntent)
        }
    }
}