package com.uzun.pseudosendydriver.presentation.ui.common

import androidx.compose.animation.core.tween
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheet(
    sheetElevation : Dp = 0.dp,
    sheetShape : Shape = RectangleShape,
    sheetContent : @Composable (onHidden: () -> Unit) -> Unit,
    activityContentScope: @Composable (onExpanded: () -> Unit) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val state = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    ModalBottomSheetLayout(
        sheetElevation = sheetElevation,
        sheetShape = sheetShape,
        sheetState = state,
        sheetContent = { sheetContent{
            scope.launch {
                state.animateTo(ModalBottomSheetValue.Hidden, tween(500))
            }
        }},
        content = { activityContentScope{
            scope.launch {
                state.animateTo(ModalBottomSheetValue.HalfExpanded, tween(500))
            }
        }}
    )
}
