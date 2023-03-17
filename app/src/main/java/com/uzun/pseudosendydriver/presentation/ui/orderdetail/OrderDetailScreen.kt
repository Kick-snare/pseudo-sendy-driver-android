package com.uzun.pseudosendydriver.presentation.ui.orderdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.OrderTag
import com.uzun.pseudosendydriver.presentation.model.OrderFullData
import com.uzun.pseudosendydriver.presentation.ui.orderdetail.OrderDetailViewModel.UiEvent
import com.uzun.pseudosendydriver.presentation.ui.theme.*
import com.uzun.pseudosendydriver.presentation.util.toCommaFormat
import kotlinx.coroutines.flow.collectLatest

@Composable
fun OrderDetailScreen(
    popUP: () -> Unit = {},
    orderInfo: OrderFullData = OrderFullData(),
    toFullSizeMap: () -> Unit = {},
    viewModel: OrderDetailViewModel = hiltViewModel(),
    snackBarState: SnackbarHostState,
) = Column(
    modifier = Modifier
        .fillMaxSize()
        .background(White)
) {

    LaunchedEffect(true) {
        val departLoc = orderInfo.departInfo.latlng
        val arriveLoc = orderInfo.arriveInfo.latlng
        viewModel.getDrivingRoute(departLoc, arriveLoc, orderInfo.wayPointList.map { it.latlng })
        viewModel.getMinifiedAddress(departLoc, false)
        viewModel.getMinifiedAddress(arriveLoc)

        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.Error -> viewModel.showSnackBar(event.message, snackBarState)
                UiEvent.FullSizeMap -> toFullSizeMap()
            }
        }
    }

    DetailTopBar(popUP)

    if (viewModel.route.path.isNotEmpty())
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            dateTimeRow(orderInfo.loadingTime)
            BasicInformationRow(
                miniAddresses = viewModel.miniAddresses,
                orderInfo = orderInfo
            )
            drivingPathRow(
                route = viewModel.route,
                onMapClick = viewModel::onMapClicked
            )
            loadNumberRow(
                orderNumber = orderInfo.orderId
            )
            locationsRow(
                depart = orderInfo.departInfo.roadAddress,
                waypoints = orderInfo.wayPointList.map { it.roadAddress },
                arrive = orderInfo.arriveInfo.roadAddress
            )

            graySpacer() // 띄우기

            tableRow(
                title = "상차일시",
                text = orderInfo.loadingTime.toStringInDetail(),
                isBold = true
            )
            tableRow(
                title = "이동거리",
                text = (viewModel.route.summary.distance / 1000.0).toCommaFormat() + "km",
            )
            tableRow(
                title = "차종",
                text = orderInfo.getVehicleDescriptionString(),
            )

            graySpacer() // 띄우기

            tableRow(
                title = "운송옵션",
                text = "기사님 운전만 필요",
            )
            tableRow(
                title = "추가인부",
                text = "0명 (없음)",
            )

            graySpacer() // 띄우기

            tableRow(
                title = "동승여부",
                text = if (OrderTag.RIDE_WITH in orderInfo.orderTagList) "O" else "X",
            )
            tableRow(
                title = "동승비",
                text = "00,000원 (고객이 현장에서 지불)",
            )
            tableRow(
                title = "작업내용",
                text = "테스트",
            )

            graySpacer() // 띄우기

            tableRow(
                title = "운송료",
                text = orderInfo.chargeCost.toCommaFormat() + "원",
            )
            tableRow(
                title = "VAT",
                text = (orderInfo.chargeCost * 0.1).toInt().toCommaFormat() + "원",
            )
            tableRow(
                title = "정산금액",
                text = (orderInfo.chargeCost * 1.1).toInt().toCommaFormat() + "원",
                isBold = true,
                color = DayRedBase
            )

            graySpacer() // 띄우기

            buttonZone() {}
        }
}

fun LazyListScope.buttonZone(onClick: () -> Unit) = item {
    Row(Modifier.padding(UIConst.SPACE_XS)) {
        Button(
            onClick = onClick,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = DayBlueBase,
                contentColor = White,
            ),
            modifier = Modifier.fillMaxWidth(),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text("오더수락", style = PseudoSendyTheme.typography.Medium.copy(color = White))
        }
    }
}

fun LazyListScope.tableRow(
    title: String,
    text: String,
    isBold: Boolean = false,
    color: Color = DayGrayscale100,
) = item {
    Row(
        Modifier
            .background(DayBackgroundSecondary)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = PseudoSendyTheme.typography.Normal.copy(color = DayGrayscale300),
            modifier = Modifier
                .width(88.dp)
                .border(1.dp, DayBorderDefault)
                .background(DayBackgroundLightGray)
                .padding(UIConst.SPACE_S),
            textAlign = TextAlign.End
        )
        Text(
            text = text,
            style =
            if (isBold) PseudoSendyTheme.typography.NormalBold.copy(color = color)
            else PseudoSendyTheme.typography.Normal.copy(color = color),
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, DayBorderDefault)
                .background(DayBackgroundPrimary)
                .padding(UIConst.SPACE_S)
        )
    }
}

fun LazyListScope.graySpacer() = item {
    Spacer(
        modifier = Modifier
            .background(DayBackgroundSecondary)
            .fillMaxWidth()
            .height(9.dp)
    )
}