package com.uzun.pseudosendydriver.presentation.ui.orderlist

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.*
import com.uzun.pseudosendydriver.presentation.model.OrderItemInfo
import com.uzun.pseudosendydriver.presentation.ui.common.LineSpacer
import com.uzun.pseudosendydriver.presentation.ui.common.OrderItem
import com.uzun.pseudosendydriver.presentation.ui.common.OrderUnitDropDownSelector
import com.uzun.pseudosendydriver.presentation.ui.theme.*
import kotlinx.coroutines.launch
import kotlin.reflect.KFunction1

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderListScreen(
    isInSheet: Boolean = false,
    orderItemList: List<OrderItemInfo>,
    sortBarEnable: Boolean = true,
    onExpanded: () -> Unit = {},
    moveToOrderDetail: (String) -> Unit = {},
    paddingValues: PaddingValues,
    sortBy: (OrderUnit) -> Unit = {},
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(top = paddingValues.calculateTopPadding())
        .padding(bottom = if (isInSheet) 0.dp else paddingValues.calculateBottomPadding()),
) {
    val pagerState = rememberPagerState()
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    OrderSelectTab(pagerState, onExpanded)

    val orderUnit = remember { mutableStateOf(OrderUnit.ByTime) }
    if (sortBarEnable) {
        SortByBar(orderUnit.value) {
            orderUnit.value = it
            sortBy(it)
            scope.launch { listState.animateScrollToItem(index = 0) }
        }
        LineSpacer()
    }


    HorizontalPager(
        pageCount = TabType.values().size,
        state = pagerState
    ) { page ->
        if (page == 0) OrderListContent(false) {}
        else OrderListContent(
            listState = listState,
            orderItemList = orderItemList,
            onItemClicked = moveToOrderDetail
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OrderSelectTab(pagerState: PagerState, onExpanded: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = DayBackgroundPrimary,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                    .clip(RoundedCornerShape(2.dp, 2.dp, 0.dp, 0.dp)),
                color = DayBlueBase,
                height = 3.dp
            )
        },
    ) {
        TabType.values().forEachIndexed { idx, tab ->
            val selected = (pagerState.currentPage == idx)
            Tab(
                selected = selected,
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
                    if (tab.iconId != 0) Icon(
                        painterResource(id = tab.iconId),
                        contentDescription = null,
                        tint = if (selected) DayBlueBase else DayGrayscale400
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
    listState: LazyListState = rememberLazyListState(),
    orderItemList: List<OrderItemInfo> = emptyList(),
    onItemClicked: (String) -> Unit = {},
) = LazyColumn(
    state = listState,
    modifier = Modifier
        .background(DayBackgroundSecondary)
        .fillMaxSize()
        .padding(horizontal = UIConst.SPACE_S),
    verticalArrangement = Arrangement.spacedBy(UIConst.SPACE_S)
) {
    item { Spacer(Modifier.size(UIConst.SPACE_S)) }

    if (enable)
        items(orderItemList) { orderItemInfo ->
            OrderItem(orderItemInfo = orderItemInfo) { onItemClicked(orderItemInfo.orderId) }
        }
    else item { PlaceHolderContent() }

    item { Spacer(Modifier.size(UIConst.SPACE_S)) }
}

@Composable
fun PlaceHolderContent() = Column(
    modifier = Modifier
        .fillMaxSize()
        .padding(top = UIConst.SPACE_XXL),
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
