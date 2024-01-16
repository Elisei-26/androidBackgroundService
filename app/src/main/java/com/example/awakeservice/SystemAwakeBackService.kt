package com.example.awakeservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.SystemClock
import android.widget.Toast
import org.greenrobot.eventbus.EventBus

class SystemAwakeBackService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        Toast.makeText(this.baseContext, "ServOnStartCommand", Toast.LENGTH_SHORT).show()
        EventBus.getDefault().post(Awaketime(SystemClock.elapsedRealtime()))
//        EventBus.getDefault().unregister(this)
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Toast.makeText(this.baseContext, "ServOnBind", Toast.LENGTH_SHORT).show()
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this.baseContext, "ServOnDestroy", Toast.LENGTH_SHORT).show()
    }
}