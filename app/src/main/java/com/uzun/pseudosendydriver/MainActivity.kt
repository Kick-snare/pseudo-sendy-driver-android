package com.uzun.pseudosendydriver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.uzun.pseudosendydriver.ui.theme.PseudoSendyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PseudoSendyTheme {
                
            }
        }
    }
}