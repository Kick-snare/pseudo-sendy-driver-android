package com.uzun.pseudosendydriver.presentation.ui.orderlist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.*
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import com.uzun.pseudosendydriver.presentation.model.SendyTime
import com.uzun.pseudosendydriver.presentation.ui.common.LineSpacer
import com.uzun.pseudosendydriver.presentation.ui.common.OrderItem
import com.uzun.pseudosendydriver.presentation.ui.common.OrderUnitDropDownSelector
import com.uzun.pseudosendydriver.presentation.ui.theme.*
import java.time.LocalDateTime

@Composable
fun OrderListScreen(
    sortBarEnable: Boolean = true,
) = Column(
    modifier = Modifier.fillMaxSize()
) {
    val orderUnit = remember { mutableStateOf(OrderUnit.ByTime) }

    if (sortBarEnable)
        SortByBar(orderUnit.value) {
            orderUnit.value = it
            Log.d("TEST", "order unit $it selected")
        }

    LineSpacer()

    val orderItemInfo = OrderItemInfo(
        true,
        SendyTime(LocalDateTime.now().plusDays(3)),
        "부산광역시 부산진구 서면로",
        "부산광역시 남구 유엔로",
        87,
        160_000,
        VehicleType.TRUCK_1T,
        VehicleOption.CARGO,
        TimeOrderTag.DAWN,
        listOf(OrderTag.CAUTION, OrderTag.RIDE_WITH, OrderTag.TIME_IMPORTANT)
    )

    OrderListContent(
        listOf(orderItemInfo, orderItemInfo, orderItemInfo, orderItemInfo)
    ) {
        // onOrderItemClicked(it)
        Log.d("TEST", "item $it selected")
    }
}

@Composable
fun SortByBar(
    orderUnit: OrderUnit,
    onOrderUnitClicked: (OrderUnit) -> Unit = {},
) = Row(
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier
        .background(DayBackgroundPrimary)
        .fillMaxWidth()
        .padding(UIConst.SPACE_S)
) {
    Icon(
        painterResource(id = R.drawable.icon_solid_filter),
        contentDescription = null
    )

    OrderUnitDropDownSelector(
        onItemClick = onOrderUnitClicked
    ) { onClick, dropdown ->
        Column {
            Row(
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .padding(UIConst.SPACE_XS),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = orderUnit.korName,
                    style = PseudoSendyTheme.typography.Normal.copy(color = DayGrayscale100)
                )
                Icon(
                    painterResource(id = R.drawable.icon_solid_drop_down),
                    contentDescription = null
                )
            }
            dropdown()
        }
    }
}

@Composable
fun OrderListContent(
    orderItemList: List<OrderItemInfo>,
    onItemClicked: (OrderItemInfo) -> Unit = {},
) = LazyColumn(
    modifier = Modifier
        .background(DayBackgroundSecondary)
        .fillMaxSize()
        .padding(horizontal = UIConst.SPACE_S),
    verticalArrangement = Arrangement.spacedBy(UIConst.SPACE_S)
) {

    item { Spacer(Modifier.size(UIConst.SPACE_S)) }

    items(orderItemList) { orderItemInfo ->
        OrderItem(orderItemInfo) { onItemClicked(orderItemInfo) }
    }

    item { Spacer(Modifier.size(UIConst.SPACE_S)) }
}
