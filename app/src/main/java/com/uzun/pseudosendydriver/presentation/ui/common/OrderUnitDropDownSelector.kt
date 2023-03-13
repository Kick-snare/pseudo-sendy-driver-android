package com.uzun.pseudosendydriver.presentation.ui.common

import android.util.Log
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import com.uzun.pseudosendydriver.presentation._enum.OrderUnit
import com.uzun.pseudosendydriver.presentation.ui.theme.PseudoSendyTheme

@Composable
fun OrderUnitDropDownSelector(
    onItemClick: (OrderUnit) -> Unit = {},
    content: @Composable (() -> Unit, @Composable () -> Unit) -> Unit,
) {
    var isDropDownMenuExpanded by remember { mutableStateOf(false) }
    content(
        { isDropDownMenuExpanded = true },
        {
            DropdownMenu(
                expanded = isDropDownMenuExpanded,
                onDismissRequest = { isDropDownMenuExpanded = false }
            ) {
                OrderUnit.values().forEach { unit ->
                    DropdownMenuItem(onClick = {
                        onItemClick(unit)
                        isDropDownMenuExpanded = false
                    }) {
                        Text(
                            text = unit.korName,
                            style = PseudoSendyTheme.typography.Small
                        )
                    }
                }
            }
        }
    )
}