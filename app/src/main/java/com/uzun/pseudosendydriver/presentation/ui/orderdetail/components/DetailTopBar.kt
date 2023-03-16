package com.uzun.pseudosendydriver.presentation.ui.orderdetail

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation.ui.theme.DayGrayscale100
import com.uzun.pseudosendydriver.presentation.ui.theme.PseudoSendyTheme
import com.uzun.pseudosendydriver.presentation.ui.theme.White

@Composable
fun DetailTopBar(
    popUp: () -> Unit,
) = TopAppBar(
    backgroundColor = White,
) {
    IconButton(popUp) {
        Icon(
            painterResource(id = R.drawable.icon_solid_back),
            contentDescription = null
        )
    }
    Spacer(modifier = Modifier.width(UIConst.SPACE_S))
    Text(
        text = "오더 상세",
        style = PseudoSendyTheme.typography.Large.copy(color = DayGrayscale100)
    )
}