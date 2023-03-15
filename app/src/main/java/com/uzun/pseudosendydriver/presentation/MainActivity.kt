package com.uzun.pseudosendydriver.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.uzun.pseudosendydriver.presentation.ui.DriverMainScreen
import com.uzun.pseudosendydriver.presentation.ui.theme.PseudoSendyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PseudoSendyTheme {
                DriverMainScreen()
            }
        }
    }
}