package com.thesetox.sync

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.activity.ComponentActivity

open class SyncActivity : ComponentActivity() {
    private var syncService: SyncService? = null
    private var bound = false

    private val connection =
        object : ServiceConnection {
            override fun onServiceConnected(
                name: ComponentName?,
                binder: IBinder?,
            ) {
                syncService = (binder as SyncService.LocalBinder).getService()
                syncService?.startSyncing()
                bound = true
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                bound = false
            }
        }

    override fun onStart() {
        super.onStart()
        Intent(this, SyncService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onStop() {
        super.onStop()
        if (bound) {
            unbindService(connection)
            bound = false
        }
    }
}
