package com.uzun.pseudosendydriver.presentation.ui.orderdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.LocationType
import com.uzun.pseudosendydriver.presentation.ui.common.LineSpacer
import com.uzun.pseudosendydriver.presentation.ui.theme.*

fun LazyListScope.locationsRow(
    depart: String,
    waypoints: List<String>,
    arrive: String,
) = item {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(UIConst.SPACE_M)
    ) {

        Box(
            Modifier
                .zIndex(-1f)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 38.dp)
                    .width(18.dp)
                    .background(DayBackgroundGray, RoundedCornerShape(9.dp, 9.dp, 0.dp, 0.dp))
            ) {}

            Column {
                PointRow(type = LocationType.LOADING, location = depart)
                waypoints.forEach {
                    PointRow(type = LocationType.WAYPOINT, location = it)
                }
            }
        }
        PointRow(type = LocationType.UNLOADING, location = arrive)
    }
    LineSpacer()
}

@Composable
fun PointRow(type: LocationType, location: String) = Box(
    modifier = Modifier.fillMaxWidth(),
    contentAlignment = Alignment.TopStart
) {
    Text(
        text = type.korName,
        style = PseudoSendyTheme.typography.Small.copy(color = DayGrayscale300)
    )
    Image(
        painterResource(id = type.imageId),
        contentDescription = null,
        modifier = Modifier
            .padding(start = 38.dp)
    )
    Column(
        modifier = Modifier
            .padding(start = 88.dp)
            .fillMaxWidth()
            .padding(bottom = UIConst.SPACE_M)
    ) {
        Text(
            text = location,
            style = PseudoSendyTheme.typography.NormalBold.copy(color = DayGrayscale100)
        )
        Text(
            text = "N층 / 엘리베이터 O",
            style = PseudoSendyTheme.typography.Small.copy(color = DayGrayscale200)
        )
    }
}