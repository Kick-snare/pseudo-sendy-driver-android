package com.uzun.pseudosendydriver.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation.ui.theme.DayBackgroundPrimary

@Composable
fun MainTopBar() = Row(
    modifier = Modifier
        .background(DayBackgroundPrimary)
        .fillMaxWidth()
        .padding(start = UIConst.SPACE_M)
        .padding(vertical = 10.dp)
) {
    Image(
        painterResource(id = R.drawable.asset_logo_driver_logo_v_3_driver_only),
        contentDescription = null
    )
}