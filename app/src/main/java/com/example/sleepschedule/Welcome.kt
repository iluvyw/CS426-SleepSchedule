package com.example.sleepschedule

import android.content.Intent
import android.os.Bundle
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

        val format: DateFormat = SimpleDateFormat("dd/MM/yyyy")
        val calendar: Calendar = Calendar.getInstance()
        calendar.setFirstDayOfWeek(Calendar.MONDAY)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        val days = arrayOfNulls<String>(7)
        for (i in 0..6) {
            days[i] = format.format(calendar.getTime())
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        var hourOfSlept : Int = 0;
        var dayOfSleep : Int = 0;
        for (i in 0..6) {
            var sleepTime : Int = sumList(getIntList(this, days[i] + "timeSleep"))
            if(sleepTime != 0) dayOfSleep++;
            hourOfSlept+=sleepTime;
        }
        hourOfSlept/=60 * dayOfSleep;
        if(hourOfSlept < 20) {
            var str : String = "";
            if(hourOfSlept > 1) str += "s"
            quote.setText("You've only slept " + hourOfSlept + " hour"+str+" a day this week.\n Please sleep more!")
            imageWelcome.setImageResource(R.drawable.sad)
        }else{
            quote.setText("You're on track! \n Keep going!")
            imageWelcome.setImageResource(R.drawable.happy)
        }

        btNextWelcome.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}