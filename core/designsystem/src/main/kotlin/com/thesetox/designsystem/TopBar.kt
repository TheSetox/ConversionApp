package com.thesetox.designsystem

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionTopBar(stringId: Int) {
    TopAppBar(
        title = {
            Text(text = stringResource(stringId))
        },
    )
}

@Preview
@Composable
private fun ConversionTopBarPreview() = ConversionTopBar(stringId = R.string.sample_title)
