package com.example.awakeservice

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private lateinit var chron1: Chronometer
    private lateinit var chron2: Chronometer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

        val sysHasBeenUpFor1: Long = SystemClock.elapsedRealtime()
        chron1 = findViewById(R.id.chronometer1)
        chron1.setBase(chron1.getBase() + sysHasBeenUpFor1)

        chron2 = findViewById(R.id.chronometer2)

        val btnStartService = findViewById<Button>(R.id.button1)
        btnStartService.setOnClickListener {
            startService(Intent(this@MainActivity, SystemAwakeBackService::class.java))
        }

        val btnStopService = findViewById<Button>(R.id.button2)
        btnStopService.setOnClickListener {
            stopService(Intent(this@MainActivity, SystemAwakeBackService::class.java))
        }
    }

    override fun startService(service: Intent?): ComponentName? {
        return super.startService(service)
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name)
    }

    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    fun onEvent(rez: Awaketime) {
        chron2.setBase(chron2.getBase() + rez.time)
    }
}
