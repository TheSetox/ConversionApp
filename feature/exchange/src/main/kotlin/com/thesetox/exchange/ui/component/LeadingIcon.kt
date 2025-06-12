package com.thesetox.exchange.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thesetox.exchange.R

@Composable
fun LeadingIcon(
    color: Color,
    drawable: Int,
) {
    Box(
        modifier =
            Modifier
                .size(45.dp)
                .background(color = color, shape = CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
        )
    }
}

@Preview
@Composable
private fun LeadingIconPreview() =
    LeadingIcon(color = Color.Red, drawable = R.drawable.arrow_upward)
