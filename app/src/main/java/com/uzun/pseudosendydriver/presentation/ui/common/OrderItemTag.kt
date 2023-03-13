package com.uzun.pseudosendydriver.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.uzun.pseudosendydriver.presentation._const.UIConst
import com.uzun.pseudosendydriver.presentation.ui.theme.*

@Composable
fun BaseOrderItemTag(
    text: String,
    @DrawableRes iconId: Int,
    color : Color,
    contentColor: Color,
) = Surface(
    shape = RoundedCornerShape(16.dp),
    color = color,
    contentColor = contentColor
) {
    Row(
        modifier = Modifier
            .padding(horizontal = UIConst.SPACE_XS)
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painterResource(id = iconId), contentDescription = null)
        Spacer(modifier = Modifier.size(UIConst.SPACE_XXS))
        Text(text = text, style = PseudoSendyTheme.typography.XS)
    }
}

@Composable
fun OrderItemDayTag(
    text: String,
    @DrawableRes iconId: Int,
    enable: Boolean,
) = BaseOrderItemTag(
    text = text,
    iconId = iconId,
    color = DayBackgroundSecondary,
    contentColor = if(enable) DayGrayscale100 else DayGrayscale400
)

@Composable
fun OrderItemNightTag(
    text: String,
    @DrawableRes iconId: Int,
    enable: Boolean,
) = BaseOrderItemTag(
    text = text,
    iconId = iconId,
    color = if(enable) DayGrayscale100 else DayGrayscale400,
    contentColor = White
)