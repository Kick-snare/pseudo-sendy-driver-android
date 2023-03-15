package com.uzun.pseudosendydriver.presentation.util

import android.graphics.Bitmap
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.drawToBitmap

@Composable
fun CaptureBitmap(
    content: @Composable () -> Unit,
): () -> Bitmap {

    val context = LocalContext.current
    val composeView = remember { ComposeView(context) }
    var rendered by remember { mutableStateOf(false) }

    fun captureBitmap(): Bitmap {
        return composeView.drawToBitmap()
    }

    LaunchedEffect(key1 = rendered) {
        if (rendered) {
            captureBitmap()
        }
    }

    AndroidView(
        factory = {
            composeView.apply {
                setContent {
                    content.invoke()
                    rendered = true
                }
            }
        }
    )

    return ::captureBitmap
}