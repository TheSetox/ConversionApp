package com.thesetox.conversionapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.thesetox.designsystem.ConversionAppTheme
import com.thesetox.sync.SyncActivity

class MainActivity : SyncActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConversionAppTheme {
            }
        }
    }
}
