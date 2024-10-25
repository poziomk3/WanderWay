package com.pwr.wanderway.presentation.commons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pwr.wanderway.R
import com.pwr.wanderway.ui.theme.AppTheme

@Composable
fun MainIcon() {
    Image(
        painter = painterResource(id = R.drawable.icon),
        contentDescription = null,
        modifier = Modifier.size(50.dp)
    )
}

@Preview
@Composable
fun MainIconPreview() {
    AppTheme {
        MainIcon()
    }
}