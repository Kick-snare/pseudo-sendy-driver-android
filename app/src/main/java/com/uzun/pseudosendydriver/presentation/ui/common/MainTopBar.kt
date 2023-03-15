package com.uzun.pseudosendydriver.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation.ui.theme.DayBackgroundPrimary
import com.uzun.pseudosendydriver.presentation.ui.theme.DayGrayscale100
import com.uzun.pseudosendydriver.presentation.ui.theme.PseudoSendyTheme

@Composable
fun MainTopBar() = Row(
    modifier = Modifier
        .height(56.dp)
        .background(DayBackgroundPrimary)
        .fillMaxWidth()
        .padding(start = UIConst.SPACE_M)
        .padding(vertical = 10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Image(
        painterResource(id = R.drawable.asset_logo_driver_logo_v_3_driver_only),
        contentDescription = null
    )
}