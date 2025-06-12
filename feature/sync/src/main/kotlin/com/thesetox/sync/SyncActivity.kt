package com.thesetox.sync

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import androidx.activity.ComponentActivity

/**
 * Activity responsible for connecting to [SyncService] and starting the
 * synchronization process once a connection is established.
 *
 * The service is bound in [onStart] and unbound in [onStop]. When the service
 * is connected a sync operation will immediately be triggered.
 */
open class SyncActivity : ComponentActivity() {
    /** Reference to the background [SyncService] once it is bound. */
    private var syncService: SyncService? = null
    private var bound = false

    /**
     * Handles callbacks from the framework when binding to [SyncService].
     */
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

    /**
     * Binds to the [SyncService] when the activity becomes visible to the user.
     */
    override fun onStart() {
        super.onStart()
        Intent(this, SyncService::class.java).also {
            bindService(it, connection, Context.BIND_AUTO_CREATE)
        }
    }

    /**
     * Unbinds from the [SyncService] when the activity is no longer visible.
     */
    override fun onStop() {
        super.onStop()
        if (bound) {
            unbindService(connection)
            bound = false
        }
    }
}
