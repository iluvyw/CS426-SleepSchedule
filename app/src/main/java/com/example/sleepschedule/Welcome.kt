package com.example.sleepschedule

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_set_alarm_time.*
import kotlinx.android.synthetic.main.activity_welcome.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Welcome : AppCompatActivity(),setValue,getValue {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        if (getBoolean(this,"used") == false){
            setBoolean(this,"used",true)
            val intent = Intent(this,SetAlarmTime::class.java)
            startActivity(intent)
            return
        }else {
            createAlarm(getInt(this,"hourRemind"), getInt(this,"minRemind"))
        }


        val format: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val calendar: Calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        val days = arrayOfNulls<String>(7)
        for (i in 0..6) {
            days[i] = format.format(calendar.getTime())
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        var hourOfSlept : Int = 0;
        var dayOfSleep : Int = 0;
        for (i in 0..6) {
            var sleepTime : Int = 0//sumList(getIntList(this, days[i] + "timeSleep"))
            if(sleepTime != 0) dayOfSleep++;
            hourOfSlept+=sleepTime;
        }
        if(dayOfSleep > 0) hourOfSlept/=60 * dayOfSleep;
        if(hourOfSlept < 20) {
            var str : String = "";
            if(hourOfSlept > 1) str += "s"
            quote.text = "You've only slept " + hourOfSlept + " hour"+str+" a day this week.\n Please sleep more!"
            imageWelcome.setImageResource(R.drawable.sad)
        }else{
            quote.text = "You're on track! \n Keep going!"
            imageWelcome.setImageResource(R.drawable.happy)
        }

        btNextWelcome.setOnClickListener{
            val intent = Intent(this,CalendarActivity::class.java)
            startActivity(intent)
        }
    }

    fun createAlarm(hour: Int, minutes: Int) {
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "Bed time!!!")
            putExtra(AlarmClock.EXTRA_HOUR, hour)
            putExtra(AlarmClock.EXTRA_MINUTES, minutes)
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    fun removeAlarm(){
        val alarmIntent = Intent(AlarmClock.ACTION_DISMISS_ALARM);
        alarmIntent.apply{putExtra(AlarmClock.ALARM_SEARCH_MODE_LABEL, "Bed time!!!")}
        startActivity(alarmIntent)
    }
}