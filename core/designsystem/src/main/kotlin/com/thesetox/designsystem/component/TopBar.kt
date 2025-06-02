package com.thesetox.designsystem.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.thesetox.designsystem.LightBlue
import com.thesetox.designsystem.R
import com.thesetox.designsystem.TopBarTitleTextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversionTopBar(stringId: Int) {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(stringId), style = TopBarTitleTextStyle) },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = LightBlue),
    )
}

@Preview
@Composable
private fun ConversionTopBarPreview() = ConversionTopBar(stringId = R.string.sample_title)
