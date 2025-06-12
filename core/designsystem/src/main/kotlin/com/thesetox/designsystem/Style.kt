package com.thesetox.designsystem

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Collection of text styles used throughout the application. Centralizing them
// here keeps typography consistent across the UI.

/** Text style for medium sized text used in general UI elements. */
val MediumTextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium)

/** Text style specifically for button labels. */
val ButtonTextStyle = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Light)

/** Text style for the title displayed in the top app bar. */
val TopBarTitleTextStyle = TextStyle(fontSize = 18.sp, color = Color.White)
