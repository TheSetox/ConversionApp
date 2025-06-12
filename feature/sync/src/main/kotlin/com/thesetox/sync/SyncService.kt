package com.thesetox.sync

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * Android [Service] responsible for keeping currency rates in sync.
 *
 * The service exposes a [LocalBinder] so activities can bind to it and
 * trigger synchronization through [startSyncing]. Once started, the
 * service executes [SyncUseCase] every five seconds on a background
 * coroutine until the binding is removed.
 */
class SyncService : Service(), KoinComponent {
    private val binder = LocalBinder()
    private var job: Job? = null

    private val sync: SyncUseCase by inject<SyncUseCase>()

    /**
     * Binder returned to clients so they can access [SyncService] methods.
     */
    inner class LocalBinder : Binder() {
        /** Returns the current [SyncService] instance. */
        fun getService(): SyncService = this@SyncService
    }

    /** Called when a client binds to the service. */
    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    /**
     * Starts periodic synchronization if it isn't already running.
     */
    fun startSyncing() {
        if (job?.isActive == true) return

        job =
            CoroutineScope(Dispatchers.IO).launch {
                while (isActive) {
                    Log.d(TAG, SYNCING)
                    Log.i(TAG, sync())
                    delay(5000)
                }
            }
    }

    /** Stops the active synchronization job, if any. */
    private fun stopSyncing() {
        job?.cancel()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        stopSyncing()
        return super.onUnbind(intent)
    }

    companion object {
        private val TAG = SyncService::class.java.simpleName
        private const val SYNCING = "syncing.."
    }
}
