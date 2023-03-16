package com.uzun.pseudosendydriver.presentation.ui.orderdetail


import androidx.compose.animation.*
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.*
import com.naver.maps.map.overlay.OverlayImage
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.data.remote.dto.RouteUnitEnt
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation.ui.orderdetail.OrderDetailViewModel.*
import com.uzun.pseudosendydriver.presentation.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalNaverMapApi::class)
fun LazyListScope.drivingPathRow(
    route: RouteUnitEnt,
    onMapClick: () -> Unit,
) = item {

    var isOpen by remember { mutableStateOf(true) }
    val rotation = remember { Animatable(180f) }
    val coroutineScope = rememberCoroutineScope()

    val onIconClick: () -> Unit = {
        isOpen = !isOpen
        coroutineScope.launch {
            if (isOpen)
                rotation.animateTo(
                    targetValue = 180f,
                    animationSpec = tween(200)
                )
            else rotation.animateTo(
                targetValue = 360f,
                animationSpec = tween(200)
            )
        }
    }

    BasicRow(
        horizontalPadding = UIConst.SPACE_XL,
        verticalPadding = UIConst.SPACE_XXS,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "운송경로 보기",
            style = PseudoSendyTheme.typography.Normal.copy(DayGrayscale100)
        )
        IconButton(
            modifier = Modifier.rotate(rotation.value),
            onClick = onIconClick
        ) {
            Icon(
                painterResource(id = R.drawable.icon_solid_drop_down),
                contentDescription = null,
            )
        }
    }

    AnimatedVisibility(
        visible = isOpen,
        enter = fadeIn() + expandVertically(),
        exit = shrinkVertically() + fadeOut(),
    ) {
        NaverMap(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp),
            cameraPositionState = CameraPositionState(
                position = CameraPosition(route.summary.getCenterPoint(), 11.0)
            ),
            properties = MapProperties(
                maxZoom = 18.0,
                minZoom = 5.0,
                isLiteModeEnabled = true,
            ),
            uiSettings = MapUiSettings(
                isZoomControlEnabled = true,
                isScrollGesturesEnabled = true,
                isZoomGesturesEnabled = false,
                isTiltGesturesEnabled = false,
                isCompassEnabled = false,
                isRotateGesturesEnabled = false,
                isScaleBarEnabled = true,
            ),
            onMapClick = { _, _ -> onMapClick.invoke() }
        ) {
            Marker(
                state = MarkerState(position = route.summary.getStartLatLng()),
                icon = OverlayImage.fromResource(R.drawable.marker_load),
            )
            Marker(
                state = MarkerState(position = route.summary.getGoalLatLng()),
                icon = OverlayImage.fromResource(R.drawable.marker_unload)
            )
            route.summary.getWaypointLatLngs().forEach {
                Marker(state = MarkerState(position = it))
            }
            if (route.path.isNotEmpty())
                PathOverlay(
                    coords = route.getPathList(),
                    width = 7.dp,
                    color = DayBlueBase,
                    outlineColor = White
                )
        }
    }
}
