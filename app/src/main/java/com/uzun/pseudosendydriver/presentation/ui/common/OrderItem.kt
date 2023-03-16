package com.uzun.pseudosendydriver.presentation.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.OrderTag
import com.uzun.pseudosendydriver.presentation._enum.TimeOrderTag
import com.uzun.pseudosendydriver.presentation._enum.VehicleOption
import com.uzun.pseudosendydriver.presentation._enum.VehicleType
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import com.uzun.pseudosendydriver.presentation.model.SendyTime
import com.uzun.pseudosendydriver.presentation.ui.theme.*
import com.uzun.pseudosendydriver.presentation.util.toCommaFormat

@Composable
fun OrderItem(
    orderItemInfo: OrderItemInfo = OrderItemInfo(),
    isMiniMode: Boolean = false,
    onClick: () -> Unit = {},
) = Surface(
    shape = RoundedCornerShape(16.dp),
    border = BorderStroke(1.dp, DayBorderDefault),
    contentColor = if (orderItemInfo.enable) DayGrayscale100 else DayGrayscale400,
    modifier = Modifier.clip(RoundedCornerShape(16.dp)).clickable(onClick = onClick)
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UIConst.SPACE_M),
        verticalArrangement = Arrangement.spacedBy(UIConst.SPACE_M)
    ) {
        OrderItemTimeInfo(orderItemInfo.loadingTime)
        OrderItemLocationInfo(orderItemInfo.departInfo.roadAddress, orderItemInfo.arriveInfo.roadAddress)
        if(!isMiniMode) {
            OrderItemTagList(
                orderItemInfo.enable,
                orderItemInfo.distance,
                orderItemInfo.vehicleType,
                orderItemInfo.vehicleOption,
                orderItemInfo.timeOrderTag,
                orderItemInfo.orderTagList
            )
            OrderItemChargeInfo(orderItemInfo.enable, orderItemInfo.chargeCost)
        }
    }
}

@Composable
fun OrderItemTimeInfo(loadingTime: SendyTime) = Box {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(UIConst.SPACE_XS),
        modifier = Modifier
            .align(Alignment.CenterStart)
            .fillMaxWidth()
    ) {
        Icon(painterResource(id = R.drawable.icon_solid_calendar), contentDescription = null)

        Text(
            text = "$loadingTime 상차",
            style = PseudoSendyTheme.typography.NormalBold
        )
    }
    val d_day = loadingTime.getDayLeft()
    Text(
        text = if(d_day==0) "D-Day" else if(d_day > 0) "D+$d_day" else "D$d_day",
        modifier = Modifier.align(Alignment.CenterEnd),
        style = PseudoSendyTheme.typography.Normal.copy(color =
            if(d_day > 0) Color.Red else Color.Green
        ),
    )
}

@Composable
fun OrderItemLocationInfo(
    departAddr: String,
    arriveAddr: String,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    Column(
        modifier = Modifier
            .height(53.dp)
            .background(DayBackgroundLightGray, RoundedCornerShape(9.dp)),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painterResource(id = R.drawable.ic_depart),
            contentDescription = null
        )
        Image(
            painterResource(id = R.drawable.ic_arrive),
            contentDescription = null
        )
    }
    Column(
        modifier = Modifier.defaultMinSize(minHeight = 61.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = departAddr,
            style = PseudoSendyTheme.typography.Medium,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = arriveAddr,
            style = PseudoSendyTheme.typography.Medium,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun OrderItemTagList(
    enable: Boolean,
    distance: Int,
    vehicleType: VehicleType,
    vehicleOption: VehicleOption,
    timeOrderTag: TimeOrderTag,
    orderTagList: List<OrderTag>,
) = FlowRow(
    modifier = Modifier.fillMaxWidth(),
    mainAxisSpacing = UIConst.SPACE_XXS,
    crossAxisSpacing = UIConst.SPACE_XXS
) {
    OrderItemDayTag("${distance}km", R.drawable.icon_solid_place, enable)
    OrderItemDayTag("${vehicleType.korName} ${vehicleOption.korName}", R.drawable.icon_solid_wheel, enable)
    OrderItemNightTag(timeOrderTag.korName, timeOrderTag.iconId, enable)
    orderTagList.forEach { tag ->
        OrderItemNightTag(tag.korName, tag.iconId, enable)
    }
}



@Composable
fun OrderItemChargeInfo(
    enable: Boolean = true,
    ChargeCost : Int = 0,
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(UIConst.BUTTON_RADIUS_NORMAL))
        .background(if (enable) DayBlueBase else DayGrayscale400)
        .padding(vertical = UIConst.SPACE_XS)
        .padding(horizontal = UIConst.SPACE_S)
    ,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Text(
        text = "운임료",
        style = PseudoSendyTheme.typography.Normal.copy(color = White)
    )
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = ChargeCost.toCommaFormat() + "원",
            style = PseudoSendyTheme.typography.MediumBold.copy(color = White),
        )
        Spacer(modifier = Modifier.size(UIConst.SPACE_XXS))
        Image(
            painterResource(id = R.drawable.icon_solid_coin),
            contentDescription = null,
        )
    }
}


