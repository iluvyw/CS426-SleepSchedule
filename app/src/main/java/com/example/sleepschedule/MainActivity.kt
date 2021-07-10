package com.example.sleepschedule

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startTestActivity(view: View) {
        val intent = Intent(this,SleepHourActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, "Dat")
        }
        startActivity(intent)
    }
}