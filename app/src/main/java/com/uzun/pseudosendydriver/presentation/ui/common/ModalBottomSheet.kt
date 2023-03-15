package com.uzun.pseudosendydriver.presentation.ui.common

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheet(
    peekHeight: Dp = 56.dp,
    sheetContent: @Composable (onExpanded: () -> Unit) -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetElevation = 0.dp,
        sheetShape = RectangleShape,
        sheetPeekHeight = peekHeight,
        sheetContent = {
            sheetContent {
                scope.launch {
                    bottomSheetScaffoldState.bottomSheetState.expand()
                }
            }
        },
    ) {
        content ()
    }
}