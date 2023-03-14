package com.uzun.pseudosendydriver.presentation.ui.orderlist

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.*
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import com.uzun.pseudosendydriver.presentation.ui.common.LineSpacer
import com.uzun.pseudosendydriver.presentation.ui.common.OrderItem
import com.uzun.pseudosendydriver.presentation.ui.common.OrderUnitDropDownSelector
import com.uzun.pseudosendydriver.presentation.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderListScreen(
    orderItemList: List<OrderItemInfo>,
    sortBarEnable: Boolean = true,
    onExpanded: () -> Unit = {},
) = Column(
    modifier = Modifier.fillMaxSize()
) {
    val pagerState = rememberPagerState()

    OrderSelectTab(pagerState, onExpanded)

    val orderUnit = remember { mutableStateOf(OrderUnit.ByTime) }
    if (sortBarEnable) {
        SortByBar(orderUnit.value) {
            orderUnit.value = it
            Log.d("TEST", "order unit $it selected")
        }
        LineSpacer()
    }


    HorizontalPager(
        pageCount = TabType.values().size,
        state = pagerState
    ) { page ->
        if (page == 0) OrderListContent(false) {}
        else OrderListContent(orderItemList = orderItemList) {}
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderSelectTab(pagerState: PagerState, onExpanded: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = DayBackgroundPrimary,
    ) {
        TabType.values().forEachIndexed { idx, tab ->
            Tab(
                selected = (pagerState.currentPage == idx),
                onClick = {
                    onExpanded()
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(idx)
                    }
                }
            ) {
                Row(
                    modifier = Modifier.padding(vertical = UIConst.SPACE_S),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (tab.iconId != 0) Image(
                        painterResource(id = tab.iconId),
                        contentDescription = null
                    )
                    Text(tab.korName)
                }
            }
        }
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
        .padding(vertical = UIConst.SPACE_XS)
        .padding(horizontal = UIConst.SPACE_S)
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
    enable: Boolean = true,
    orderItemList: List<OrderItemInfo> = emptyList(),
    onItemClicked: (OrderItemInfo) -> Unit = {},
) = LazyColumn(
    modifier = Modifier
        .background(DayBackgroundSecondary)
        .fillMaxSize()
        .padding(horizontal = UIConst.SPACE_S),
    verticalArrangement = Arrangement.spacedBy(UIConst.SPACE_S)
) {
    item { Spacer(Modifier.size(UIConst.SPACE_S)) }

    if (enable)
        items(orderItemList) { orderItemInfo ->
            OrderItem(orderItemInfo) { onItemClicked(orderItemInfo) }
        }
    else
        item {
            Column(
                modifier = Modifier.fillMaxSize().padding(top = UIConst.SPACE_XXL),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(id = R.drawable.img_empty_orderlist),
                    contentDescription = null
                )
                Spacer(Modifier.size(UIConst.SPACE_M))
                Text(
                    text = "할당된 오더가\n없습니다.",
                    style = PseudoSendyTheme.typography.XL.copy(color = DayGrayscale300),
                    textAlign = TextAlign.Center
                )
            }
        }

    item { Spacer(Modifier.size(UIConst.SPACE_S)) }
}
