package com.uzun.pseudosendydriver.presentation.ui.orderdetail

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Text
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation.ui.theme.DayGrayscale100
import com.uzun.pseudosendydriver.presentation.ui.theme.PseudoSendyTheme

fun LazyListScope.loadNumberRow(orderNumber: String) = item {
    BasicRow(
        horizontalPadding = UIConst.SPACE_M,
        verticalPadding = UIConst.SPACE_S,
    ) {
        Text(
            text = "화물 번호 : $orderNumber",
            style = PseudoSendyTheme.typography.Normal.copy(color = DayGrayscale100)
        )
    }
}