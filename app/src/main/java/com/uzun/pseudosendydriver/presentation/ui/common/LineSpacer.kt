package com.uzun.pseudosendydriver.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendydriver.presentation.ui.theme.DayBorderDefault

@Composable
fun LineSpacer() = Spacer(
    Modifier
        .background(DayBorderDefault)
        .fillMaxWidth()
        .height(1.dp)
)