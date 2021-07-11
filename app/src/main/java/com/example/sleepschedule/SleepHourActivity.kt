package com.example.sleepschedule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.stefanodp91.android.circularseekbar.CircularSeekBar
import com.github.stefanodp91.android.circularseekbar.OnCircularSeekBarChangeListener
import kotlinx.android.synthetic.main.activity_sleep_hour.*

class SleepHourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep_hour)
        seek.setOnRoundedSeekChangeListener(object : OnCircularSeekBarChangeListener {
            /**
             * Progress change
             * @param CircularSeekBar
             * @param progress the progress
             */

            override fun onProgressChange(CircularSeekBar: CircularSeekBar, progress: Float) {
                var minute: Int = (progress*720/100).toInt();
                var hour: Int = minute/60;
                minute -= hour * 60;
                var string_min: String = if (minute >= 10) minute.toString() else "0$minute";

                CircularSeekBar.text = "$hour:$string_min";
            }

            /**
             * Indicator touched
             * @param CircularSeekBar
             */
            override fun onStartTrackingTouch(CircularSeekBar: CircularSeekBar) {}

            /**
             * Indicator released
             * @param CircularSeekBar
             */
            override fun onStopTrackingTouch(CircularSeekBar: CircularSeekBar) {}
        })
    }
}