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

class SyncService : Service(), KoinComponent {
    private val binder = LocalBinder()
    private var job: Job? = null

    private val sync: SyncUseCase by inject<SyncUseCase>()

    inner class LocalBinder : Binder() {
        fun getService(): SyncService = this@SyncService
    }

    override fun onBind(intent: Intent?): IBinder {
        return binder
    }

    fun startSyncing() {
        if (job?.isActive == true) return

        job =
            CoroutineScope(Dispatchers.IO).launch {
                while (isActive) {
                    Log.d(TAG, "syncing..")
                    Log.i(TAG, sync())
                    delay(5000)
                }
            }
    }

    private fun stopSyncing() {
        job?.cancel()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        stopSyncing()
        return super.onUnbind(intent)
    }

    companion object {
        private val TAG = SyncService::class.java.simpleName
    }
}
