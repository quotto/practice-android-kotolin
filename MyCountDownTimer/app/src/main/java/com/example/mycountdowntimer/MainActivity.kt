package com.example.mycountdowntimer

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var soundPool: SoundPool
    private var soundResId = 0

    inner class MyCountDownTimer(millisInFuture: Long,countDownInterval: Long) :
                CountDownTimer(millisInFuture, countDownInterval) {
        var isRunning = false

        override fun onTick(millisUntilFinished: Long) {
            val minute = millisUntilFinished / 1000L / 60L
            val second = millisUntilFinished / 1000L % 60L
            timerText.text = "%1d:%2$02d".format(minute, second)
        }

        override fun onFinish() {
            timerText.text = "0:00"
            soundPool.play(soundResId, 1.0f, 100f, 0, 0, 1.0f)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerText.text = "3:00"
        val timer = MyCountDownTimer(3 * 60 * 1000, 100)
        playStop.setOnClickListener {
            timer.isRunning = when(timer.isRunning) {
                true -> {
                    timer.cancel()
                    playStop.setImageResource(
                        R.drawable.ic_play_arrow_black_24dp
                    )
                    false
                }
                false -> {
                    timer.start()
                    playStop.setImageResource(
                        R.drawable.ic_stop_black_24dp
                    )
                    true
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        soundPool = if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            SoundPool(2, AudioManager.STREAM_ALARM, 0)
        } else {
            val audioAttributes = AudioAttributes.Builder().setUsage(AudioManager.STREAM_ALARM).build()
            SoundPool.Builder().setMaxStreams(2).setAudioAttributes(audioAttributes).build()
        }
        soundResId = soundPool.load(this, R.raw.bellsound, 1)
    }

    override fun onPause() {
        super.onPause()
        soundPool.release()
    }
}
