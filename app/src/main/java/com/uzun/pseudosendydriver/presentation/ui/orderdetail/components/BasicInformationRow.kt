package com.uzun.pseudosendydriver.presentation.ui.orderdetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation.model.MiniAddresses
import com.uzun.pseudosendydriver.presentation.model.OrderFullData
import com.uzun.pseudosendydriver.presentation.ui.common.OrderItemTagList
import com.uzun.pseudosendydriver.presentation.ui.theme.DayGrayscale100
import com.uzun.pseudosendydriver.presentation.ui.theme.PseudoSendyTheme

fun LazyListScope.BasicInformationRow(
    miniAddresses: MiniAddresses,
    orderInfo: OrderFullData,
) = item {
    BasicRow(
        verticalPadding = UIConst.SPACE_XL,
        horizontalPadding = UIConst.SPACE_XL
    ) {
        Column {
            FromToText(miniAddresses)
            Spacer(Modifier.size(UIConst.SPACE_XS))
            OrderItemTagList(
                orderInfo.enable,
                orderInfo.distance,
                orderInfo.vehicleType,
                orderInfo.vehicleOption,
                orderInfo.timeOrderTag,
                orderInfo.orderTagList
            )
        }
    }
}

@Composable
fun FromToText(miniAddresses: MiniAddresses) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceEvenly,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = miniAddresses.depart,
        style = PseudoSendyTheme.typography.XLBold.copy(color = DayGrayscale100)
    )
    Icon(
        painterResource(id = R.drawable.icon_solid_chevron_right),
        contentDescription = null
    )
    Text(
        text = miniAddresses.arrive,
        style = PseudoSendyTheme.typography.XLBold.copy(color = DayGrayscale100)
    )
}