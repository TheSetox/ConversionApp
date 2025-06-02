package com.thesetox.exchange.ui.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.thesetox.designsystem.ButtonTextStyle
import com.thesetox.designsystem.LightBlue
import com.thesetox.exchange.R

@Composable
fun SubmitButton(onClick: () -> Unit = {}) {
    Button(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = LightBlue),
        onClick = onClick,
    ) {
        Text(
            text = stringResource(R.string.submit_button).uppercase(),
            style = ButtonTextStyle,
        )
    }
}
