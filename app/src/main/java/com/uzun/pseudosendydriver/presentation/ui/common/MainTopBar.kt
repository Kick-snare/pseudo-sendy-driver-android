package com.uzun.pseudosendydriver.presentation.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst

@Composable
fun MainTopBar() = Row(
    modifier = Modifier
        .padding(start = UIConst.SPACE_M)
        .padding(top = UIConst.SPACE_M)
        .padding(bottom = UIConst.SPACE_L)
) {
    Image(
        painterResource(id = R.drawable.asset_logo_driver_logo_v_3_driver_only),
        contentDescription = null
    )
}