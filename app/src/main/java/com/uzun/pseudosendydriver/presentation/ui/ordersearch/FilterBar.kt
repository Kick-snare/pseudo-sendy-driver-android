package com.uzun.pseudosendydriver.presentation.ui.ordersearch

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.uzun.pseudosendydriver.R
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation._enum.FilterType
import com.uzun.pseudosendydriver.presentation.ui.common.LineSpacer
import com.uzun.pseudosendydriver.presentation.ui.theme.*

@Composable
fun FilterBar(
    filterEnable: Map<FilterType, Boolean>,
    onFilterSelected: (FilterType) -> Unit = {},
) = Column(modifier = Modifier.fillMaxWidth(),) {
    LineSpacer()
    Row(
        modifier = Modifier
            .background(DayBackgroundSecondary)
            .fillMaxWidth()
            .padding(start = UIConst.SPACE_S)
            .padding(vertical = UIConst.SPACE_S),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(UIConst.SPACE_XS)

    ) {
        Icon(
            painterResource(id = R.drawable.icon_solid_filter),
            contentDescription = null
        )
        FilterType.values().forEach { filterType ->
            FilterCard(
                filterType.korName,
                filterEnable[filterType]!!,
            ) { onFilterSelected(filterType) }
        }
    }
    LineSpacer()
}

@Composable
fun FilterCard(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) = Surface(
    shape = RoundedCornerShape(UIConst.BUTTON_RADIUS_XL),
    contentColor = if (selected) White else DayGrayscale100,
    color = if (selected) DayBlueBase else Color.Transparent,
    border = BorderStroke(1.dp, DayBorderDefault),
) {
    Row(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = UIConst.SPACE_XS)
            .padding(vertical = 2.dp)
            .padding(UIConst.SPACE_XXS)
        ,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(UIConst.SPACE_XXS)
    ) {
        if (selected)
            Icon(
                painterResource(id = R.drawable.icon_solid_checked),
                contentDescription = null
            )
        Text(
            text = title,
            style = PseudoSendyTheme.typography.Small
        )
    }
}